// Immediately-invoked function expression
(function () {
    // Load the script
    var script = document.createElement("SCRIPT");
    script.src = 'https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js';
    script.type = 'text/javascript';
    script.onload = function () {
        var $ = window.jQuery;

        var out = "";
        $('.im-title a:not(".im-usage")').each(function () {
            out += $(this).attr('href').replace('/artifact/', '') + "\n";
        })

        console.log(out)
    };
    document.getElementsByTagName("head")[0].appendChild(script);
})();