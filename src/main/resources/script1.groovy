import org.mule.api.transport.*;

log.debug("Operation IS " +payload.OP  );
log.debug( "Payload class " + payload.getClass() );
log.debug( "Payload " + payload  );
log.debug( "Payload data" + payload.pd  );
if( payload == null || !payload.containsKey('pd') || !payload.containsKey('OP') )
{
	throw new Exception( "Must provide OP and pd paramters. ")
	
}

if( payload != null &&  java.util.HashMap.getClass().isInstance(payload.getClass()) && payload.containsKey('pd') )
{
	def jsonResult = JsonUtil.slurpit( payload.pd );
	 log.debug("JSON ---------------> " + jsonResult );
	def cn =  JsonUtil.getCn( jsonResult.PrimaryName );

	def ldapMap = [:];

	/*
	for( r in jsonResult.CoPersonRole )	{		log.info( "------ !!!!! " + r );	} */
	
	
	
	// roles need to be parsed based on active dates.
	//def roles = parseRolesByDates( jsonResult.CoPersonRole );
	
	ldapMap["mail"] = JsonUtil.getMailAddys(jsonResult.EmailAddress);
	ldapMap["o"] = jsonResult.Co.name;
	ldapMap["cn"] = cn;
	ldapMap["givenName"] = jsonResult.PrimaryName.given;
	ldapMap["sn"] = jsonResult.PrimaryName.family;
	ldapMap["uid"] = JsonUtil.getIdentifierBasedOnType( jsonResult.Identifier, "wikiname" );
	ldapMap["eduPersonPrincipalName"] = JsonUtil.getIdentifierBasedOnType( jsonResult.Identifier, "eppn" );
	ldapMap["employeeNumber"] = JsonUtil.getIdentifierBasedOnType( jsonResult.Identifier, "employeeNumber" );
	ldapMap["isMemberOf"] = JsonUtil.getGroupMemberships( jsonResult.CoGroupMember );
	ldapMap["eduPersonAffiliation"] = JsonUtil.getAffls( jsonResult.CoPersonRole  );

	// TODO - externalize elements of dn that are hard coded
	ldapMap["dn"] =  "employeeNumber=" + JsonUtil.getIdentifierBasedOnType( jsonResult.Identifier, "employeeNumber" ) +",ou=people,o="  + jsonResult.Co.name +",dc=gw-astronomy,dc=org" ;
	def objectClass = [
		"eduMember",
		"eduPerson",
		"inetOrgPerson",
		"organizationalPerson",
		"person"
	];
	ldapMap["objectClass"] = objectClass; 
	
	log.debug("\nldapMap ---------------> " + ldapMap.toString() );
	// this is handy for error reporting
	message.setProperty( "op", payload.OP, PropertyScope.INVOCATION);
	message.setProperty( "cn", cn, PropertyScope.INVOCATION);
	message.setProperty( "dn", ldapMap["dn"], PropertyScope.INVOCATION);	
	
	
	jsonResult = null;
	
	return ldapMap;
}
else
{
	return "foobar!!!!!!";
}

private List parseRolesByDates( Object roles )
{
	
	log.debug( "roles " +  roles.getClass().getName() + "  " + roles );
	java.util.ArrayList roleList = new java.util.ArrayList();
	
/*	log.warn( "roles is List?? "    + ( roles instanceof java.util.List )  );  */
	
	if( ! roles instanceof java.util.List ) // LasyList may give us List or Map
	{
	 
		roleList.add( roles );
		 
	}
	else
	 roleList = roles;
	
	
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
	
	java.util.Date now = new java.util.Date(); 
	
	def parsedRoles = [];

	
	for( Map r in roles )
	{		
		if( !r.valid_from.empty && r.valid_from.length() > 0 )  // is valid_from provided?
			if( now.compareTo( sdf.parse(r.valid_from) ) < 0 ) // is the valid_from in the future?
				continue; // skip - don't add this to roles to provision.			
		if( !r.valid_through.empty && r.valid_through.length() > 0 )  // is valid_through provided?
			if( sdf.parse(r.valid_through).compareTo( now ) < 0 ) // is it passed the valid_through
				continue; // skip - don't add this to roles to provision.
				
		parsedRoles.add(r)			
	}
	
	return parsedRoles;
	
}