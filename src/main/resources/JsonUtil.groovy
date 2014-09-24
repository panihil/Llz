import groovy.json.JsonSlurper;
 
import java.lang.ref.ReferenceQueue.Null;
import java.util.List;
import java.util.Map;



class JsonUtil
{

	// TODO - move to properties OR fix incomming data to not use abreviations.
	public static	def affMap =  ['F':'Faculty','SU':'Student','SA':'Staff','AL':'Alum','M':'Member','AF':'Affiliate','E':'Employee','L':'Library Walk-In'];

	/**
	 * main is just for test
	 * @param args
	 */
	static main(args)
	{

		def theMap = [:];
	def jsonText =	"{\"CoPerson\":{\"id\":\"24\",\"co_id\":\"4\",\"status\":\"A\",\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:42\"},\"Co\":{\"id\":\"4\",\"name\":\"CO-2\",\"description\":\"Second CO\",\"status\":\"A\",\"created\":\"2014-08-29 13:05:51\",\"modified\":\"2014-08-29 13:05:51\"},\"PrimaryName\":{\"id\":\"48\",\"honorific\":\"\",\"given\":\"ruth\",\"middle\":\"\",\"family\":\"rooter\",\"suffix\":\"\",\"type\":\"O\",\"language\":\"\",\"co_person_id\":\"24\",\"org_identity_id\":null,\"primary_name\":true,\"created\":\"2014-08-29 17:48:02\",\"modified\":\"2014-08-29 17:48:02\"},\"CoOrgIdentityLink\":[{\"id\":\"11\",\"co_person_id\":\"24\",\"org_identity_id\":\"24\",\"created\":\"2014-08-29 17:48:02\",\"modified\":\"2014-08-29 17:48:02\",\"OrgIdentity\":{\"id\":\"24\",\"affiliation\":\"F\",\"title\":\"\",\"o\":\"American U.\",\"ou\":\"\",\"organization_id\":null,\"co_id\":\"4\",\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-09-19 20:37:43\",\"Identifier\":[{\"id\":\"58\",\"identifier\":\"ruth@acme.org\",\"type\":\"eppn\",\"login\":true,\"status\":\"A\",\"co_person_id\":null,\"org_identity_id\":\"24\",\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\"}]}}],\"CoPersonRole\":[{\"id\":\"24\",\"co_person_id\":\"24\",\"sponsor_co_person_id\":null,\"cou_id\":\"11\",\"affiliation\":\"M\",\"title\":\"Title2\",\"o\":\"CO-2\",\"ou\":\"\",\"valid_from\":null,\"valid_through\":null,\"status\":\"A\",\"created\":\"2014-09-20 22:34:43\",\"modified\":\"2014-09-22 11:12:10\",\"Cou\":{\"id\":\"11\",\"co_id\":\"4\",\"name\":\"CO-2.COUB\",\"description\":\"\",\"parent_id\":null,\"lft\":\"11\",\"rght\":\"12\",\"created\":\"2014-09-17 15:25:49\",\"modified\":\"2014-09-17 15:25:49\"},\"Address\":[],\"TelephoneNumber\":[]},{\"id\":\"25\",\"co_person_id\":\"24\",\"sponsor_co_person_id\":null,\"cou_id\":\"10\",\"affiliation\":\"F\",\"title\":\"\",\"o\":\"CO-2\",\"ou\":\"\",\"valid_from\":null,\"valid_through\":null,\"status\":\"A\",\"created\":\"2014-09-22 13:51:28\",\"modified\":\"2014-09-22 13:51:28\",\"Cou\":{\"id\":\"10\",\"co_id\":\"4\",\"name\":\"CO-2.COUA\",\"description\":\"\",\"parent_id\":null,\"lft\":\"9\",\"rght\":\"10\",\"created\":\"2014-08-29 17:44:18\",\"modified\":\"2014-08-29 17:44:18\"},\"Address\":[],\"TelephoneNumber\":[]}],\"EmailAddress\":[{\"id\":\"47\",\"mail\":\"mmanske2@gmail.com\",\"type\":\"O\",\"verified\":false,\"co_person_id\":\"24\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\"}],\"Identifier\":[{\"id\":\"59\",\"identifier\":\"ruth@acme.org\",\"type\":\"eppn\",\"login\":true,\"status\":\"A\",\"co_person_id\":\"24\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\"},{\"id\":\"60\",\"identifier\":\"CO21\",\"type\":\"employeeNumber\",\"login\":false,\"status\":\"A\",\"co_person_id\":\"24\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:48:42\",\"modified\":\"2014-08-29 17:48:42\"},{\"id\":\"61\",\"identifier\":\"ruthrooter1\",\"type\":\"wikiname\",\"login\":false,\"status\":\"A\",\"co_person_id\":\"24\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:48:42\",\"modified\":\"2014-08-29 17:48:42\"}],\"SshKey\":[],\"CoGroupMember\":[{\"id\":\"76\",\"co_group_id\":\"16\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\",\"CoGroup\":{\"id\":\"16\",\"co_id\":\"4\",\"name\":\"gw-astronomy:CO-2\",\"description\":\"Members of CO-2\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 15:16:52\",\"modified\":\"2014-08-29 15:16:52\"}},{\"id\":\"77\",\"co_group_id\":\"17\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\",\"CoGroup\":{\"id\":\"17\",\"co_id\":\"4\",\"name\":\"gw-astronomy:CO-2:list\",\"description\":\"authorization group for list\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 15:17:31\",\"modified\":\"2014-08-29 15:17:31\"}},{\"id\":\"78\",\"co_group_id\":\"18\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\",\"CoGroup\":{\"id\":\"18\",\"co_id\":\"4\",\"name\":\"gw-astronomy:CO-2:wiki\",\"description\":\"authorization group for wiki\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 15:18:06\",\"modified\":\"2014-08-29 15:18:06\"}},{\"id\":\"79\",\"co_group_id\":\"15\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:49:42\",\"modified\":\"2014-08-29 17:49:42\",\"CoGroup\":{\"id\":\"15\",\"co_id\":\"4\",\"name\":\"admin\",\"description\":\"CO-2 Administrators\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 13:05:51\",\"modified\":\"2014-08-29 13:05:51\"}},{\"id\":\"83\",\"co_group_id\":\"20\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:56:32\",\"modified\":\"2014-08-29 17:56:32\",\"CoGroup\":{\"id\":\"20\",\"co_id\":\"4\",\"name\":\"admin:CO-2.COUA\",\"description\":\"CO-2.COUA Administrators\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 17:44:18\",\"modified\":\"2014-08-29 17:44:18\"}}]}";
	   // def jsonText ="{\"CoPerson\":{\"id\":\"24\",\"co_id\":\"4\",\"status\":\"A\",\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:42\"},\"Co\":{\"id\":\"4\",\"name\":\"CO-2\",\"description\":\"Second CO\",\"status\":\"A\",\"created\":\"2014-08-29 13:05:51\",\"modified\":\"2014-08-29 13:05:51\"},\"PrimaryName\":{\"id\":\"48\",\"honorific\":\"\",\"given\":\"ruth\",\"middle\":\"\",\"family\":\"rooter\",\"suffix\":\"\",\"type\":\"O\",\"language\":\"\",\"co_person_id\":\"24\",\"org_identity_id\":null,\"primary_name\":true,\"created\":\"2014-08-29 17:48:02\",\"modified\":\"2014-08-29 17:48:02\"},\"CoOrgIdentityLink\":[{\"id\":\"11\",\"co_person_id\":\"24\",\"org_identity_id\":\"24\",\"created\":\"2014-08-29 17:48:02\",\"modified\":\"2014-08-29 17:48:02\",\"OrgIdentity\":{\"id\":\"24\",\"affiliation\":\"F\",\"title\":\"\",\"o\":\"American U.\",\"ou\":\"\",\"organization_id\":null,\"co_id\":\"4\",\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-09-19 20:37:43\",\"Identifier\":[{\"id\":\"58\",\"identifier\":\"ruth@acme.org\",\"type\":\"eppn\",\"login\":true,\"status\":\"A\",\"co_person_id\":null,\"org_identity_id\":\"24\",\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\"}]}}],\"CoPersonRole\":[{\"id\":\"15\",\"co_person_id\":\"24\",\"sponsor_co_person_id\":null,\"cou_id\":\"10\",\"affiliation\":\"M\",\"title\":\"member\",\"o\":\"CO-2\",\"ou\":\"\",\"valid_from\":\"2014-08-29 00:00:00\",\"valid_through\":\"2015-09-20 23:59:59\",\"status\":\"A\",\"created\":\"2014-08-29 17:48:02\",\"modified\":\"2014-09-22 11:14:55\",\"Cou\":{\"id\":\"10\",\"co_id\":\"4\",\"name\":\"CO-2.COUA\",\"description\":\"\",\"parent_id\":null,\"lft\":\"9\",\"rght\":\"10\",\"created\":\"2014-08-29 17:44:18\",\"modified\":\"2014-08-29 17:44:18\"},\"Address\":[],\"TelephoneNumber\":[]},{\"id\":\"24\",\"co_person_id\":\"24\",\"sponsor_co_person_id\":null,\"cou_id\":\"11\",\"affiliation\":\"M\",\"title\":\"Title2\",\"o\":\"CO-2\",\"ou\":\"\",\"valid_from\":null,\"valid_through\":null,\"status\":\"A\",\"created\":\"2014-09-20 22:34:43\",\"modified\":\"2014-09-22 11:12:10\",\"Cou\":{\"id\":\"11\",\"co_id\":\"4\",\"name\":\"CO-2.COUB\",\"description\":\"\",\"parent_id\":null,\"lft\":\"11\",\"rght\":\"12\",\"created\":\"2014-09-17 15:25:49\",\"modified\":\"2014-09-17 15:25:49\"},\"Address\":[],\"TelephoneNumber\":[]}],\"EmailAddress\":[{\"id\":\"47\",\"mail\":\"mmanske2@gmail.com\",\"type\":\"O\",\"verified\":false,\"co_person_id\":\"24\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\"}],\"Identifier\":[{\"id\":\"59\",\"identifier\":\"ruth@acme.org\",\"type\":\"eppn\",\"login\":true,\"status\":\"A\",\"co_person_id\":\"24\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\"},{\"id\":\"60\",\"identifier\":\"CO21\",\"type\":\"employeeNumber\",\"login\":false,\"status\":\"A\",\"co_person_id\":\"24\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:48:42\",\"modified\":\"2014-08-29 17:48:42\"},{\"id\":\"61\",\"identifier\":\"ruthrooter1\",\"type\":\"wikiname\",\"login\":false,\"status\":\"A\",\"co_person_id\":\"24\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:48:42\",\"modified\":\"2014-08-29 17:48:42\"}],\"SshKey\":[],\"CoGroupMember\":[{\"id\":\"76\",\"co_group_id\":\"16\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\",\"CoGroup\":{\"id\":\"16\",\"co_id\":\"4\",\"name\":\"gw-astronomy:CO-2\",\"description\":\"Members of CO-2\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 15:16:52\",\"modified\":\"2014-08-29 15:16:52\"}},{\"id\":\"77\",\"co_group_id\":\"17\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\",\"CoGroup\":{\"id\":\"17\",\"co_id\":\"4\",\"name\":\"gw-astronomy:CO-2:list\",\"description\":\"authorization group for list\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 15:17:31\",\"modified\":\"2014-08-29 15:17:31\"}},{\"id\":\"78\",\"co_group_id\":\"18\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\",\"CoGroup\":{\"id\":\"18\",\"co_id\":\"4\",\"name\":\"gw-astronomy:CO-2:wiki\",\"description\":\"authorization group for wiki\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 15:18:06\",\"modified\":\"2014-08-29 15:18:06\"}},{\"id\":\"79\",\"co_group_id\":\"15\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:49:42\",\"modified\":\"2014-08-29 17:49:42\",\"CoGroup\":{\"id\":\"15\",\"co_id\":\"4\",\"name\":\"admin\",\"description\":\"CO-2 Administrators\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 13:05:51\",\"modified\":\"2014-08-29 13:05:51\"}},{\"id\":\"83\",\"co_group_id\":\"20\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:56:32\",\"modified\":\"2014-08-29 17:56:32\",\"CoGroup\":{\"id\":\"20\",\"co_id\":\"4\",\"name\":\"admin:CO-2.COUA\",\"description\":\"CO-2.COUA Administrators\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 17:44:18\",\"modified\":\"2014-08-29 17:44:18\"}}]}";
		//  def jsonText ="{\"CoPerson\":{\"id\":\"24\",\"co_id\":\"4\",\"status\":\"A\",\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:42\"},\"Co\":{\"id\":\"4\",\"name\":\"CO-2\",\"description\":\"Second CO\",\"status\":\"A\",\"created\":\"2014-08-29 13:05:51\",\"modified\":\"2014-08-29 13:05:51\"},\"PrimaryName\":{\"id\":\"48\",\"honorific\":\"\",\"given\":\"ruth\",\"middle\":\"\",\"family\":\"rooter\",\"suffix\":\"\",\"type\":\"O\",\"language\":\"\",\"co_person_id\":\"24\",\"org_identity_id\":null,\"primary_name\":true,\"created\":\"2014-08-29 17:48:02\",\"modified\":\"2014-08-29 17:48:02\"},\"CoOrgIdentityLink\":[{\"id\":\"11\",\"co_person_id\":\"24\",\"org_identity_id\":\"24\",\"created\":\"2014-08-29 17:48:02\",\"modified\":\"2014-08-29 17:48:02\",\"OrgIdentity\":{\"id\":\"24\",\"affiliation\":\"F\",\"title\":\"\",\"o\":\"American U.\",\"ou\":\"\",\"organization_id\":null,\"co_id\":\"4\",\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-09-19 20:37:43\",\"Identifier\":[{\"id\":\"58\",\"identifier\":\"ruth@acme.org\",\"type\":\"eppn\",\"login\":true,\"status\":\"A\",\"co_person_id\":null,\"org_identity_id\":\"24\",\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\"}]}}],\"CoPersonRole\":{\"1\":{\"id\":\"24\",\"co_person_id\":\"24\",\"sponsor_co_person_id\":null,\"cou_id\":\"11\",\"affiliation\":\"M\",\"title\":\"\",\"o\":\"CO-2\",\"ou\":\"\",\"valid_from\":null,\"valid_through\":null,\"status\":\"A\",\"created\":\"2014-09-20 22:34:43\",\"modified\":\"2014-09-20 22:34:43\",\"Cou\":{\"id\":\"11\",\"co_id\":\"4\",\"name\":\"CO-2.COUB\",\"description\":\"\",\"parent_id\":null,\"lft\":\"11\",\"rght\":\"12\",\"created\":\"2014-09-17 15:25:49\",\"modified\":\"2014-09-17 15:25:49\"},\"Address\":[],\"TelephoneNumber\":[]}},\"EmailAddress\":[{\"id\":\"47\",\"mail\":\"mmanske2@gmail.com\",\"type\":\"O\",\"verified\":false,\"co_person_id\":\"24\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\"}],\"Identifier\":[{\"id\":\"59\",\"identifier\":\"ruth@acme.org\",\"type\":\"eppn\",\"login\":true,\"status\":\"A\",\"co_person_id\":\"24\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\"},{\"id\":\"60\",\"identifier\":\"CO21\",\"type\":\"employeeNumber\",\"login\":false,\"status\":\"A\",\"co_person_id\":\"24\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:48:42\",\"modified\":\"2014-08-29 17:48:42\"},{\"id\":\"61\",\"identifier\":\"ruthrooter1\",\"type\":\"wikiname\",\"login\":false,\"status\":\"A\",\"co_person_id\":\"24\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:48:42\",\"modified\":\"2014-08-29 17:48:42\"}],\"SshKey\":[],\"CoGroupMember\":[{\"id\":\"76\",\"co_group_id\":\"16\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\",\"CoGroup\":{\"id\":\"16\",\"co_id\":\"4\",\"name\":\"gw-astronomy:CO-2\",\"description\":\"Members of CO-2\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 15:16:52\",\"modified\":\"2014-08-29 15:16:52\"}},{\"id\":\"77\",\"co_group_id\":\"17\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\",\"CoGroup\":{\"id\":\"17\",\"co_id\":\"4\",\"name\":\"gw-astronomy:CO-2:list\",\"description\":\"authorization group for list\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 15:17:31\",\"modified\":\"2014-08-29 15:17:31\"}},{\"id\":\"78\",\"co_group_id\":\"18\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:48:01\",\"modified\":\"2014-08-29 17:48:01\",\"CoGroup\":{\"id\":\"18\",\"co_id\":\"4\",\"name\":\"gw-astronomy:CO-2:wiki\",\"description\":\"authorization group for wiki\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 15:18:06\",\"modified\":\"2014-08-29 15:18:06\"}},{\"id\":\"79\",\"co_group_id\":\"15\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:49:42\",\"modified\":\"2014-08-29 17:49:42\",\"CoGroup\":{\"id\":\"15\",\"co_id\":\"4\",\"name\":\"admin\",\"description\":\"CO-2 Administrators\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 13:05:51\",\"modified\":\"2014-08-29 13:05:51\"}},{\"id\":\"83\",\"co_group_id\":\"20\",\"co_person_id\":\"24\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:56:32\",\"modified\":\"2014-08-29 17:56:32\",\"CoGroup\":{\"id\":\"20\",\"co_id\":\"4\",\"name\":\"admin:CO-2.COUA\",\"description\":\"CO-2.COUA Administrators\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 17:44:18\",\"modified\":\"2014-08-29 17:44:18\"}}]}";
		//def jsonText = "{\"CoPerson\":{\"id\":\"25\",\"co_id\":\"4\",\"status\":\"A\",\"created\":\"2014-08-29 17:54:21\",\"modified\":\"2014-08-29 17:56:55\"},\"Co\":{\"id\":\"4\",\"name\":\"CO-2\",\"description\":\"Second CO\",\"status\":\"A\",\"created\":\"2014-08-29 13:05:51\",\"modified\":\"2014-08-29 13:05:51\"},\"PrimaryName\":{\"id\":\"50\",\"honorific\":\"Poobah    \",\"given\":\"al\",\"middle\":\"marvelo\",\"family\":\"albert\",\"suffix\":\" Esq\",\"type\":\"O\",\"language\":\"\",\"co_person_id\":\"25\",\"org_identity_id\":null,\"primary_name\":true,\"created\":\"2014-08-29 17:54:22\",\"modified\":\"2014-08-29 17:54:22\"},\"CoOrgIdentityLink\":[{\"id\":\"12\",\"co_person_id\":\"25\",\"org_identity_id\":\"25\",\"created\":\"2014-08-29 17:54:22\",\"modified\":\"2014-08-29 17:54:22\",\"OrgIdentity\":{\"id\":\"25\",\"affiliation\":null,\"title\":null,\"o\":\"American U.\",\"ou\":null,\"organization_id\":null,\"co_id\":\"4\",\"created\":\"2014-08-29 17:54:21\",\"modified\":\"2014-09-02 11:52:30\",\"Identifier\":[{\"id\":\"62\",\"identifier\":\"al@acme.org\",\"type\":\"eppn\",\"login\":true,\"status\":\"A\",\"co_person_id\":null,\"org_identity_id\":\"25\",\"created\":\"2014-08-29 17:54:21\",\"modified\":\"2014-08-29 17:54:21\"}]}}],\"CoPersonRole\":[{\"id\":\"16\",\"co_person_id\":\"25\",\"sponsor_co_person_id\":null,\"cou_id\":\"10\",\"affiliation\":\"M\",\"title\":\"member\",\"o\":\"CO-2\",\"ou\":null,\"valid_from\":\"2014-08-29 00:00:00\",\"valid_through\":\"2015-08-29 00:00:00\",\"status\":\"A\",\"created\":\"2014-08-29 17:54:22\",\"modified\":\"2014-08-29 17:56:55\",\"Cou\":{\"id\":\"10\",\"co_id\":\"4\",\"name\":\"CO-2.COUA\",\"description\":\"\",\"parent_id\":null,\"lft\":\"9\",\"rght\":\"10\",\"created\":\"2014-08-29 17:44:18\",\"modified\":\"2014-08-29 17:44:18\"},\"Address\":[],\"TelephoneNumber\":[]},{\"id\":\"23\",\"co_person_id\":\"25\",\"sponsor_co_person_id\":null,\"cou_id\":\"11\",\"affiliation\":\"M\",\"title\":\"Associate\",\"o\":\"CO-2\",\"ou\":\"\",\"valid_from\":\"2014-09-17 00:00:00\",\"valid_through\":\"2015-09-30 23:59:59\",\"status\":\"A\",\"created\":\"2014-09-17 15:26:23\",\"modified\":\"2014-09-17 15:27:32\",\"Cou\":{\"id\":\"11\",\"co_id\":\"4\",\"name\":\"CO-2.COUB\",\"description\":\"\",\"parent_id\":null,\"lft\":\"11\",\"rght\":\"12\",\"created\":\"2014-09-17 15:25:49\",\"modified\":\"2014-09-17 15:25:49\"},\"Address\":[],\"TelephoneNumber\":[]}],\"EmailAddress\":[{\"id\":\"49\",\"mail\":\"mmanske2@gmail.com\",\"type\":\"O\",\"verified\":false,\"co_person_id\":\"25\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:54:22\",\"modified\":\"2014-08-29 17:54:22\"},{\"id\":\"58\",\"mail\":\"foo@bar.com\",\"type\":\"O\",\"verified\":false,\"co_person_id\":\"25\",\"org_identity_id\":null,\"created\":\"2014-09-18 17:41:21\",\"modified\":\"2014-09-19 12:53:38\"}],\"Identifier\":[{\"id\":\"63\",\"identifier\":\"al@acme.org\",\"type\":\"eppn\",\"login\":true,\"status\":\"A\",\"co_person_id\":\"25\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:54:22\",\"modified\":\"2014-08-29 17:54:22\"},{\"id\":\"64\",\"identifier\":\"CO22\",\"type\":\"employeeNumber\",\"login\":false,\"status\":\"A\",\"co_person_id\":\"25\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:56:56\",\"modified\":\"2014-08-29 17:56:56\"},{\"id\":\"65\",\"identifier\":\"alalbert1\",\"type\":\"wikiname\",\"login\":false,\"status\":\"A\",\"co_person_id\":\"25\",\"org_identity_id\":null,\"created\":\"2014-08-29 17:56:56\",\"modified\":\"2014-08-29 17:56:56\"}],\"SshKey\":[],\"CoGroupMember\":[{\"id\":\"80\",\"co_group_id\":\"16\",\"co_person_id\":\"25\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:54:21\",\"modified\":\"2014-08-29 17:54:21\",\"CoGroup\":{\"id\":\"16\",\"co_id\":\"4\",\"name\":\"gw-astronomy:CO-2\",\"description\":\"Members of CO-2\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 15:16:52\",\"modified\":\"2014-08-29 15:16:52\"}},{\"id\":\"81\",\"co_group_id\":\"17\",\"co_person_id\":\"25\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:54:21\",\"modified\":\"2014-08-29 17:54:21\",\"CoGroup\":{\"id\":\"17\",\"co_id\":\"4\",\"name\":\"gw-astronomy:CO-2:list\",\"description\":\"authorization group for list\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 15:17:31\",\"modified\":\"2014-08-29 15:17:31\"}},{\"id\":\"82\",\"co_group_id\":\"18\",\"co_person_id\":\"25\",\"member\":true,\"owner\":false,\"created\":\"2014-08-29 17:54:22\",\"modified\":\"2014-08-29 17:54:22\",\"CoGroup\":{\"id\":\"18\",\"co_id\":\"4\",\"name\":\"gw-astronomy:CO-2:wiki\",\"description\":\"authorization group for wiki\",\"open\":false,\"status\":\"A\",\"created\":\"2014-08-29 15:18:06\",\"modified\":\"2014-08-29 15:18:06\"}},{\"id\":\"97\",\"co_group_id\":\"21\",\"co_person_id\":\"25\",\"member\":true,\"owner\":false,\"created\":\"2014-09-17 15:43:49\",\"modified\":\"2014-09-17 15:43:49\",\"CoGroup\":{\"id\":\"21\",\"co_id\":\"4\",\"name\":\"admin:CO-2.COUB\",\"description\":\"CO-2.COUB Administrators\",\"open\":false,\"status\":\"A\",\"created\":\"2014-09-17 15:25:49\",\"modified\":\"2014-09-17 15:25:49\"}}]}";


		JsonSlurper jsonSlurper = new JsonSlurper();
		Map jsonResult = (Map)jsonSlurper.parseText(jsonText);



		/*		for( Object k in jsonResult.keySet() )
		 {
		 println("-----------> " +  k.getClass()  );
		 println(  k);
		 }*/

		def person = jsonResult.CoPerson;
		def groups = jsonResult.CoGroupMember;
		def roles = jsonResult.CoPersonRole;
		def identifier = jsonResult.Identifier;


		println( roles.getClass().getName() );

 		
		println( "Roles size: " + roles.size() );

 

		theMap["mail"] = getMailAddys(jsonResult.EmailAddress);
		theMap["o"] = jsonResult.Co.name;
		theMap["cn"] = getCn( jsonResult.PrimaryName );
		theMap["givenName"] = jsonResult.PrimaryName.given;
		theMap["sn"] = jsonResult.PrimaryName.family;
		theMap["uid"] = getIdentifierBasedOnType( identifier, "wikiname" );
		theMap["eduPersonPrincipalName"] = getIdentifierBasedOnType( identifier, "eppn" );
		theMap["employeeNumber"] = getIdentifierBasedOnType( identifier, "employeeNumber" );
		theMap["isMemberOf"] = getGroupMemberships( groups );
		theMap["eduPersonAffiliation"] = getAffls( roles  );

		println( theMap );
	}

	 
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
