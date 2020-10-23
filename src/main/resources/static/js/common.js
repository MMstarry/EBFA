$(function () {
    $("input:not([autocomplete])").attr('autocomplete', 'off');
})





function wmsparamsMatter(value,row,index) {
    var span=document.createElement('span');
    span.setAttribute('title',value);
    if(value){
        span.innerHTML = value;
    }
    return span.outerHTML;
}

function wmsformatTableUnit(value, row, index) {
    return {
        css: {
            "white-space": 'nowrap',
            "text-overflow": 'ellipsis',
            "overflow": 'hidden'
        }
    }
}