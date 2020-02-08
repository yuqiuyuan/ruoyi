

//统计每页载入次数，级每个模块点击次数
//注意：1.定义1个变量。_pageId  页面id
//			2.引用本页时，放在文件最下方

function search(on1win){
	var month = jQuery("#month").val();
	if(on1win){
		document.location = "/zt/search.do?month="+month;
	}else{
		window.open("/zt/search.do?month="+month,"_blank");
	}
}

//更改每个模块的点击次数
function inpmv(pmid){
	/*if(typeof(pmid)=='undefined' ||pmid==null || pmid==''){
		return ;
	}
	var url = '//img.docin.com/stat/controllers/inpmv_c.php';
	var c_name="cookie_id"; 
	var c_value=getcookie(c_name);//读
	url=url+'?mid='+pmid+'&cookie_id='+c_value;    
  	docinclick(url);*/
	
	inpmv_new(pmid);
}

//更改每个模块的点击次数,新的
function inpmv_new(pmid){
	if( typeof(pmid)=='undefined' ||pmid==null || pmid==''){
		return ;
	}
	var url = '//img.docin.com/app/load/module?mid='+pmid;
  	docinclick(url);
}

function inpmvdup(mid,dupid){
	if( typeof(mid)=='undefined' ||mid==null || mid==''){
		return ;
	}
	if( typeof(dupid)=='undefined' ||dupid==null || dupid==''){
		return ;
	}
	var url = '//img.docin.com/app/load/moduleEdup?mid='+mid+"&dupid="+dupid;
	docinclick(url);
}

//更改每个页面的载入次数
function inpv(){    
  	/*if( typeof(_pageId)=='undefined' ||_pageId==null || _pageId==''){
		return ;
	}
	var url = '//img.docin.com/stat/controllers/inpv_c.php';
    var c_name="cookie_id"; 
	var c_value=getcookie(c_name);//读
	url=url+'?pid='+_pageId+'&cookie_id='+c_value;
	if(typeof(js_pid)!='undefined' && js_pid!=null){
		url += '&proid='+js_pid;
	}
  	docinclick(url);*/
	
  	//这里也统计一下
  	inpv_new();
}

//更改每个页面的载入次数
function inpv_new(){    
  	if( typeof(_pageId)=='undefined' ||_pageId==null || _pageId==''){
		return ;
	}
	var url = '//img.docin.com/app/load/page?pid='+_pageId;
  	docinclick(url);
}

function inpv_new_v2(tmp_pageId){    
  	if( typeof(tmp_pageId)=='undefined' ||tmp_pageId==null || tmp_pageId==''){
		return ;
	}
	var url = '//img.docin.com/app/load/page?pid='+tmp_pageId;
  	docinclick(url);
}
 
 //更改每个页面的载入次数
function inpv_tmp(_newpageId){    
	/*var url = '//img.docin.com/stat/controllers/inpv_c.php';
    var c_name="cookie_id"; 
	var c_value=getcookie(c_name);//读
	url=url+'?pid='+_pageId+'&cookie_id='+c_value;
	if(typeof(js_pid)!='undefined' && js_pid!=null){
		url += '&proid='+js_pid;
	}
  	docinclick(url);*/
	
	if( typeof(_newpageId)=='undefined' ||_newpageId==null || _newpageId==''){
		return ;
	}
	inpv_new_v2(_newpageId);
}
//更改有热门文档页面的载入次数
function inpv_newtmp(_newpageId){
	/*if( typeof(_newpageId)=='undefined' ||_newpageId==null || _newpageId==''){
		return ;
	}
	var url = '//img.docin.com/stat/controllers/inpv_c.php';
    var c_name="cookie_id"; 
	var c_value=getcookie(c_name);//读
	url=url+'?pid='+_newpageId+'&cookie_id='+c_value;
	if(typeof(js_pid)!='undefined' && js_pid!=null){
		url += '&proid='+js_pid;
	}
  	docinclick(url);*/
	if( typeof(_newpageId)=='undefined' ||_newpageId==null || _newpageId==''){
		return ;
	}
	inpv_new_v2(_newpageId);
}

function QSinclude(script_filename) {
    var html_doc = document.getElementsByTagName('head')[0];
    var js = document.createElement('script');
    js.setAttribute('language', 'javascript');
    js.setAttribute('type', 'text/javascript');
    js.setAttribute('src', script_filename);
	if(typeof html_doc == "undefined"){
		document.body.appendChild(js);
	}else{
	    html_doc.appendChild(js);
	}
    return false;
}

function getCurrentTime(c){
	var d, s = "";
	d = new Date();
	s += d.getHours() + c;
	s += d.getMinutes() + c;
	s += d.getSeconds() + c;
	s += d.getMilliseconds();
	return s;
}

function getcookie(name){ 
	var cookieArray=document.cookie.split("; "); //得到分割的cookie名值对 
	var cookie=new Object(); 
	for(var i=0;i<cookieArray.length;i++){ 
		var arr=cookieArray[i].split("="); //将名和值分开 
		if(arr[0]==name)return unescape(arr[1]); //如果是指定的cookie，则返回它的值 
	} 
	return ""; 
}

function docinclick(url) {
    d = new Date();
    if(document.images) {
		(new Image()).src=url+ "&time=" + d.getTime();
    }
    return true;
}

function uppv(pageid , looktime){
	/*var url = '//img.docin.com/stat/controllers/inpv_c.php';
	var c_name="cookie_id"; 
	var c_value=getcookie(c_name);//读
	url=url+'?pid='+pageid+'&cookie_id='+c_value + '&looktime=' + looktime;    
  	docinclick(url);*/
	
	inpv_new_v2(pageid);
} 
//专为相关文档固定id的专题做统计用的
function inpv_right_gdzt(){  
  	/*if( typeof(_pageId_right_gdzt)=='undefined' ||_pageId_right_gdzt==null || _pageId_right_gdzt==''){
		return ;
	}
	var url = '//img.docin.com/stat/controllers/inpv_c.php';
    var c_name="cookie_id"; 
	var c_value=getcookie(c_name);//读
	url=url+'?pid='+_pageId_right_gdzt+'&cookie_id='+c_value;
	if(typeof(js_pid)!='undefined' && js_pid!=null){
		url += '&proid='+js_pid;
	}
  	docinclick(url);*/
	
	//inpv_new();
	if( typeof(_pageId_right_gdzt)=='undefined' ||_pageId_right_gdzt==null || _pageId_right_gdzt==''){
		return ;
	}
	inpv_new_v2(_pageId_right_gdzt);
}

//专为25%终极页统计
function inpv_for25_end(){  
  	/*if( typeof(_pageId_for25_end)=='undefined' ||_pageId_for25_end==null || _pageId_for25_end==''){
		return ;
	}
	var url = '//img.docin.com/stat/controllers/inpv_c.php';
    var c_name="cookie_id"; 
	var c_value=getcookie(c_name);//读
	url=url+'?pid='+_pageId_for25_end+'&cookie_id='+c_value;
	if(typeof(js_pid)!='undefined' && js_pid!=null){
		url += '&proid='+js_pid;
	}
  	docinclick(url);*/
	if( typeof(_pageId_for25_end)=='undefined' ||_pageId_for25_end==null || _pageId_for25_end==''){
		return ;
	}
	inpv_new_v2(_pageId_for25_end);
}
//专为触屏版终极页百分点做的统计
function inpv_touchEnd_BFD(){  
  	/*if( typeof(_pageId_touchEnd_BFD)=='undefined' ||_pageId_touchEnd_BFD==null || _pageId_touchEnd_BFD==''){
		return ;
	}
	var url = '//img.docin.com/stat/controllers/inpv_c.php';
    var c_name="cookie_id"; 
	var c_value=getcookie(c_name);//读
	url=url+'?pid='+_pageId_touchEnd_BFD+'&cookie_id='+c_value;
	if(typeof(js_pid)!='undefined' && js_pid!=null){
		url += '&proid='+js_pid;
	}
  	docinclick(url);*/
	if( typeof(_pageId_touchEnd_BFD)=='undefined' ||_pageId_touchEnd_BFD==null || _pageId_touchEnd_BFD==''){
		return ;
	}
	inpv_new_v2(_pageId_touchEnd_BFD);
}
//专为触屏版pdf2html终极页做的统计
function inpv_touchEnd_pdf2html(){  
  	/*if( typeof(_pageId_touchEnd_pdf2html)=='undefined' ||_pageId_touchEnd_pdf2html==null || _pageId_touchEnd_pdf2html==''){
		return ;
	}
	var url = '//img.docin.com/stat/controllers/inpv_c.php';
    var c_name="cookie_id"; 
	var c_value=getcookie(c_name);//读
	url=url+'?pid='+_pageId_touchEnd_pdf2html+'&cookie_id='+c_value;
	if(typeof(js_pid)!='undefined' && js_pid!=null){
		url += '&proid='+js_pid;
	}
  	docinclick(url);*/
	if( typeof(_pageId_touchEnd_pdf2html)=='undefined' ||_pageId_touchEnd_pdf2html==null || _pageId_touchEnd_pdf2html==''){
		return ;
	}
	inpv_new_v2(_pageId_touchEnd_pdf2html)
}

//通用的页面统计
function inpv_common(){  
  	/*if( typeof(_pageId_common)=='undefined' ||_pageId_common==null || _pageId_common==''){
		return ;
	}
	var url = '//img.docin.com/stat/controllers/inpv_c.php';
    var c_name="cookie_id"; 
	var c_value=getcookie(c_name);//读
	url=url+'?pid='+_pageId_common+'&cookie_id='+c_value;
	if(typeof(js_pid)!='undefined' && js_pid!=null){
		url += '&proid='+js_pid;
	}
  	docinclick(url);*/
	if( typeof(_pageId_common)=='undefined' ||_pageId_common==null || _pageId_common==''){
		return ;
	}
  	inpv_new_v2(_pageId_common);
}

//pc ImgVsFlash终极页对比测试
function inpv_ImgVsFlash(){
    if(typeof(_pageId_ImgVsFlash) == 'undefined' || _pageId_ImgVsFlash == null || _pageId_ImgVsFlash == ''){
        return ;
    }
    inpv_new_v2(_pageId_ImgVsFlash);
}

//触屏版9折载入统计
function inpv_touchDisCount(){  
	if( typeof(_pageId_touchDisCount)=='undefined' ||_pageId_touchDisCount==null || _pageId_touchDisCount==''){
		return ;
	}
	inpv_new_v2(_pageId_touchDisCount);
}



//触屏版充值并购买
function inpv_finAndBuy(){  
	if( typeof(_pageId_buy)=='undefined' ||_pageId_buy==null || _pageId_buy==''){
		return ;
	}
	inpv_new_v2(_pageId_buy);
}

function inpv_pages(pageIds){
	if( typeof(pageIds)=='undefined' ||pageIds==null || pageIds==''){
		return ;
	}
	var url = '//img.docin.com/app/load/page?pid='+pageIds;
  	docinclick(url);
}

//PC端终极页各cookie载入量。cIndex是cookie尾数下标（0-15）
function inpv_pcDocEndPageByCookie(cIndex){
	if( typeof(cIndex)=='undefined' ||cIndex==null || cIndex===''){
		return ;
	}
	var _moduleId = 6613 + cIndex;
	inpmv_new(_moduleId);
}
//PC端搜索页各cookie载入量。cIndex是cookie尾数下标（0-15）
function inpv_pcDocSearchPageByCookie(cIndex){
	if( typeof(cIndex)=='undefined' ||cIndex==null || cIndex===''){
		return ;
	}
	var _moduleId = 6629 + cIndex;
	inpmv_new(_moduleId);
}
//统计多个模块id。减少网络请求
function inpv_modules(mids){
	if( typeof(mids)=='undefined' ||mids==null || mids===''){
		return ;
	}
	inpmv_new(mids);
}

inpv();//增加页面载入次数

//pc 搜索无结果页的载入次数
function inpv_noSearchResult(){
    if(typeof(_pageId_noSearchResult) == 'undefined' || _pageId_noSearchResult == null || _pageId_noSearchResult == ''){
        return ;
    }
    inpv_new_v2(_pageId_noSearchResult);
}

function inpv_uv(mid,un){
	if( typeof(mid)=='undefined' ||mid==null || mid===''){
		return ;
	}
	if( typeof(un)=='undefined' ||un==null || un===''){
		return ;
	}
	var url = '//img.docin.com/app/load/univist?mid='+mid+"&un="+un;
  	docinclick(url);
}
