chrome.contextMenus.create({
    "type" : "normal",
    "title" : "选择元素加入Purified",
    "contexts" : ["all"],
    "onclick" : add_to_purified
});

function add_to_purified(info, tab){
    alert(123);
    if ($) alert(3333333);
}
