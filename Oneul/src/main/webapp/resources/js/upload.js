function checkImageType(fileName){
	
	var pattern = /jpg|gif|png|jpeg/i;
	
	return fileName.match(pattern);

}

function getFileInfo(fullName){
		
	var fileName, imgsrc, getLink;
	
	var fileLink;
	
	if(checkImageType(fullName)){
		imgsrc = "http://localhost/file/displayFile?fileName="+fullName;
		fileLink = fullName.substr(12);		
		getLink = "http://localhost/file/displayFile?fileName="+fullName;
	}else{
		imgsrc ="/resources/image/lief_default_x64.png";
		fileLink = fullName.substr(12);
		getLink = "http://localhost/file/displayFile?fileName="+fullName;
	}
	fileName = fileLink.substr(fileLink.indexOf("_")+1);
	
	return  {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
	
}


