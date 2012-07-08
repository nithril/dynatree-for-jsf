function changeContentSize(objId, scrollerId) {
	var id = objId.substr(0, objId.length - 4);
	var contentId = id + "ContentDiv";
	var comp = document.getElementById(contentId);
	if (comp == null) {
		comp = document.getElementById(objId);
		if (comp != null) {
			var ctn = "Container";
			comp = document.getElementById(objId.substr(0, objId.length
					- ctn.length)
					+ "ContentDiv");
			if (comp == null) {
				return;
			}
		}
	}
	var height = parseInt(comp.style.height);
	var width = parseInt(comp.style.width);
	var scroller = document.getElementById(scrollerId);
	if (height > 70) {
		scroller.style.height = "" + (height - 55) + "px";
	}
}