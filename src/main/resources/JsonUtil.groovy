import groovy.json.JsonSlurper;

import java.lang.ref.ReferenceQueue.Null;
import java.util.List;
import java.util.Map;



class JsonUtil
{

   // TODO - move to properties OR fix incomming data to not use abreviations.
   public static	def affMap =  ['F':'Faculty','SU':'Student','SA':'Staff','AL':'Alum','M':'Member','AF':'Affiliate','E':'Employee','L':'Library Walk-In'];


	
   /**
	* JsonSlurper may make a lazy map or it may make a list. This makes dealing with them tricky. So, this takes
	* an object. If is is a list, it just returns it. If it is a HashMap, it packs it into an ArrayList and returns it.
	*
	* @param thing
	* @return
	*/
   public static List convertLazyMapToList( Object thing )
   {
	   if( thing instanceof java.util.List ) return thing;
	   else
	   {
		   java.util.ArrayList retVal = new java.util.ArrayList();
		   retVal.add( thing );
		   return retVal;
	   }
   }

   public static Map slurpit( String jsonStr )
   {
	   JsonSlurper jsonSlurper = new JsonSlurper();
	   return (Map)jsonSlurper.parseText(jsonStr) ;
   }

   public static List getMailAddys( List mails)
   {
	   def mailList = [];

	   for( Map m in mails )
	   {
		   mailList.add( m.mail );
	   }


	   return mailList;
   }

   public static String getIdentifierBasedOnType( List identifier, String type )
   {
	   String retVal = "";
	   for( Map i in identifier )
	   {
		   if( i.type == type )
		   {

			   retVal = i.identifier;
			   break;
		   }
	   }

	   return retVal;
   }

   /**
	*
	* @param groups
	* @return
	*/
   public static List getGroupMemberships( List groups ) {
	   def groupList = [];
	   for( Map g in groups ) {
		   groupList.add(g.CoGroup.name);
	   }
	   return groupList;
   }

   /**
	* eduPersonAffiliation is multi valued so we return a string in the form <cou name><separator string><title>
	*
	* @param roles - list of role objects.
	* @param sep - a String to separate cou name from title. default is '|'
	* @return a string
	*/
   public static  String getAffls ( List roles, String sep = "|" ) {
	   StringBuilder eduPersonAffiliation = new StringBuilder();

	   for( Map r in roles) {
		
		   if( eduPersonAffiliation.length() > 0 ) eduPersonAffiliation.append(",");
		   
		   def couNameVal = (!r.Cou.name.empty) ? r.Cou.name : r.Cou.title;
			eduPersonAffiliation.append( couNameVal  + sep )
		   
		   def roleTitleVal ;
		   if( !r.title.empty )
			   roleTitleVal = r.title;
		   else
		   {
			   roleTitleVal = affMap[r.affiliation];
		   }
		   
		   
		   eduPersonAffiliation.append( roleTitleVal );
	   }

	   return eduPersonAffiliation.toString();
   }
   /**
	* Abstracted to function because of low confidence!
	* @param pn
	* @return
	*/
   public static String getCn( Map pn )
   {
	   StringBuilder retVal = new StringBuilder();
	   // honorific
	   if( !pn.honorific.empty )
		   retVal.append( pn.honorific.trim() + " ");
	   // given
	   if( !pn.given.empty )
		   retVal.append( pn.given.trim() + " ");
	   // middle
	   if( !pn.middle.empty )
		   retVal.append( pn.middle.trim() + " ");
	   // family
	   if( !pn.family.empty )
		   retVal.append( pn.family.trim() + " ");
	   // suffix
	   if( !pn.suffix.empty )
		   retVal.append( pn.suffix.trim() + " ");

	   return  retVal.toString().trim();
   }
}