/**
 * 课程表的格式化工具
 */

	function formatTime(obj){
		var str = new String(obj);
		var arr = str.split('-');
		var week = getWeek(arr[0]);
		var time = getClass(arr[1]);
		return week+"，"+time;
	}
	
	function getWeek(str){
		switch(str){
			case "1":
				return "周一";
			case "2":
				return "周二";
			case "3":
				return "周三";
			case "4":
				return "周四";
			case "5":
				return "周五";
			default:
				return null;
		}
	}
	
	function getClass(str){
		switch(str){
		case "1":
			return "第1，2节";
		case "2":
			return "第3，4节";
		case "3":
			return "第5，6节";
		case "4":
			return "第7，8节";
		default:
			return null;
	    }
	}
	
	function loadXmlReq(){
		var xmlReq;
		if(window.XMLHttpRequest){
			xmlReq = new XMLHttpRequest();
		}else{
			xmlReq = new ActiveObject('Microsoft.XMLHTTP');
		}
		return xmlReq;
	}
	function sended(xmlReq,url){
		xmlReq.open("POST",url,true);
        xmlReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlReq.send();
	}
	


