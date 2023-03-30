@groovy.transform.BaseScript com.ibm.dbb.groovy.ScriptLoader baseScript
import com.ibm.dbb.build.*
import groovy.transform.*

//@Field def bindUtils= loadScript(new File("${props.zAppBuildDir}/utilities/BindUtilities.groovy"))
//@Field BuildProperties props = BuildProperties.getInstance()
	
def copySysprint(String prnPDS, String member, File logFile, String headLine) {
	
	
	println "***** Ray Lam Sysprint $prnPDS $member $logFile $headLine"
	
	
	switch (headLine) {
		
		   case "COMPILE":
		     copyCompile(logFile)
			 println("Call COMPILE"); //this won't match
			 break;
		
			 case "LINKEDIT":
			 copylinkEdit(logFile)
			 println("Call LINKEDIT"); //this won't match
			 break;
			
			 case "DASHES":
			 copyDashes(logFile)
			 println("Call DASHES"); //this won't match
			 break;
			
		     case "COMMENTS":
		     copyComments(logFile)
			 println("Call COMMENTS");  //this won't match
			 break;
				
		    case "COPY2PDS":
		    	
			  new CopyToPDS().file(new File("$logFile"))
			      .hfsEncoding("UTF-8")
		          .dataset(prnPDS)
		          .pdsEncoding("IBM-1047")
		          .member(member)
                  .execute()
		
			
			     println("CopyTOPDS")
			     String printPDS = prnPDS + "($member)"
				 return printPDS
				 break;
			
		    default:
             println("no match");
		}
	
	return

}


// Straight call to copy2PDS  Method

def copy2PDS(String prnPDS, String member, File logFile) {
	
	new CopyToPDS().file(new File("$logFile"))
    	.hfsEncoding("UTF-8")
    	.dataset(prnPDS)
    	.pdsEncoding("IBM-1047")
    	.member(member)
    	.execute()


   println("Copy2PDS")
   String printPDS = prnPDS + "($member)"
   return printPDS
		
}

def headLines(File logFile, String headLine, String headersPDS) {
	
	println "***** Ray Lam HEADLINES $headLine"

/*
        boolean torf = true

        if (headline = 'COMPILE')
        {
           torf = false
        }

	
	CopyToHFS copyCmd = new CopyToHFS();
	copyCmd.setDataset("$headersPDS");
	copyCmd.setMember("$headLine");
	copyCmd.setFile(new File("$logFile"));
    copyCmd.hfsEncoding("UTF-8");
    copyCmd.append(true);
	copyCmd.copy();
*/
	
	new CopyToHFS().file(new File("$logFile"))
    .hfsEncoding("UTF-8")
    .dataset("$headersPDS")
    .member("$headLine")
    .append(true)
    .execute()
		
	return

}

// All the following methods have been repplaced by the "headlines" method .

def copyDashes(File logFile) {
	
	println "***** Ray Lam DASHED $logFile"
	
	CopyToHFS copyCmd = new CopyToHFS();
	copyCmd.setDataset("RLAM.IDZAPP.HEADERS");
	copyCmd.setMember("DASHES");
	copyCmd.setFile(new File("$logFile"));
	copyCmd.append(true);
	copyCmd.copy();
	
		
	return logFile

}

def copyComments(File logFile) {
	
	println "***** Ray Lam COMMENTS $logFile"
	
	CopyToHFS copyCmd = new CopyToHFS();
	copyCmd.setDataset("RLAM.IDZAPP.HEADERS");
	copyCmd.setMember("COMMENTS");
	copyCmd.setFile(new File("$logFile"));
	copyCmd.append(true);
	copyCmd.copy();
	
		
	return logFile

}

def copyCompile(File logFile) {
	
	println "***** Ray Lam COMPILE $logFile"
	
	CopyToHFS copyCmd = new CopyToHFS();
	copyCmd.setDataset("RLAM.IDZAPP.HEADERS");
	copyCmd.setMember("COMPILE");
	copyCmd.setFile(new File("$logFile"));
	copyCmd.append(true);
	copyCmd.copy();
	
		
	return logFile

}

def copylinkEdit(File logFile) {
	
	println "***** Ray Lam LINKEDIT $logFile"
	
	CopyToHFS copyCmd = new CopyToHFS();
	copyCmd.setDataset("RLAM.IDZAPP.HEADERS");
	copyCmd.setMember("LINKEDIT");
	copyCmd.setFile(new File("$logFile"));
	copyCmd.append(true);
	copyCmd.copy();
	
		
	return logFile

}