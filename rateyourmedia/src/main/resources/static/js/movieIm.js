$(document).ready(function () {
    showCover();
});

//Quelle https://codepen.io/pixelnik/pen/pgWQBZ Code natürlich an meinen Anwendungsfall angepasst.


var showCover =function() {
    var cover = $('#poster');
    var film = $('#title').text();
    $.getJSON("https://api.themoviedb.org/3/search/movie?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&query=" + film + "&callback=?", function(json) {
        if (json != "Nothing found.") {
            cover.attr('src', "http://image.tmdb.org/t/p/w500/" + json.results[0].poster_path);
        } else {
            $('#img').html('<div class="alert"><p>Sorry, no Image available</p></div>');
        }});
}
