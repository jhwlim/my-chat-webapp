var input = document.getElementsByClassName('search__input')[0];
var items = document.getElementsByClassName('search__item');
var clear = document.getElementsByClassName('search__figure--clear')[0];

function getCurrentIndexOfSearchItem() {
    for (var i = 0; i < items.length; i++) {
        if (items[i].classList.contains('search__item--selected')) {
            return i;
        }
    }
    return -1; // 포함하고 있지 않다면 -1을 반환
}
function setSearchItemSelected(index) {
    items[index].classList.add('search__item--selected');
    input.value = items[index].innerText;
    items[index].scrollIntoView();
}
function removeSearchItemSelected(index) {
    items[index].classList.remove('search__item--selected');
}

input.addEventListener('keydown', function(e) {
    var keyCode = e.keyCode;
    var items = document.getElementsByClassName('search__item');
    var currentIndex = getCurrentIndexOfSearchItem();
    
    if (items.length > 0) {
    
        if (keyCode == 40) { // down
            if (currentIndex >= 0) {
                removeSearchItemSelected(currentIndex);
            }
            setSearchItemSelected(currentIndex+1 > items.length-1 ? 0 : currentIndex+1);
        } else if (keyCode == 38) { // up
            e.preventDefault();
    
            if (currentIndex >= 0) {
                removeSearchItemSelected(currentIndex);
            } 
            setSearchItemSelected(currentIndex-1 < 0 ? items.length-1 : currentIndex-1);
        }
    }

    if (keyCode == 13) { // enter
        console.log(input.value);
    }
});

clear.addEventListener('click', function() {
    input.value = "";
})
