function add_to_purified(info, tab){
    alert('"'+info.linkUrl+'"已加入Purfied!');
}

chrome.contextMenus.create({
    "type" : "normal",
    "title" : "加入我的Purified",
    "id" : "purified",
    "contexts" : ["link"],
    "onclick" : add_to_purified
});