//Inspirierend Quelle für showPoster siehe movieIM.js; showCover wurde selbst geschrieben.

function showAllCover(){
    showCover(0);
    showCover(1);
    showCover(2);
    showPoster(0);
    showPoster(1);
    showPoster(2);
}

function showCover(i) {
        var cover = $('#cover'+i);
        var title = $('#Btitle'+i).text();
        var author = $('#author'+i).text();
        var url = null;
        $.get("https://www.googleapis.com/books/v1/volumes?q=" + title + "+" + author + "&maxResults=1&projection=lite", function (request, response) {
            cover.attr('src', request.items[0].volumeInfo.imageLinks.thumbnail);
            url = request.items[0].volumeInfo.imageLinks.thumbnail;
        });
        if (url == null) {
            $.get("https://www.googleapis.com/books/v1/volumes?q=" + title + "&maxResults=1&projection=lite", function (request, response) {
                cover.attr('src', request.items[0].volumeInfo.imageLinks.thumbnail);
            });
        }
    return true;
}

var showPoster =function(i) {
    var poster = $('#poster'+i);
    var film = $('#Mtitle'+i).text();
    $.getJSON("https://api.themoviedb.org/3/search/movie?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&query=" + film + "&callback=?", function(json) {
        if (json != "Nothing found.") {
            poster.attr('src', "http://image.tmdb.org/t/p/w500/" + json.results[0].poster_path);
        } else {
            $('#img').html('<div class="alert"><p>Sorry, no Image available</p></div>');
        }});
}