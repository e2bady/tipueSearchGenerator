# Tipque Search Generator

Tipque is a Search Engine for static website created by http://tipque.com. http://netzhaft.de just extended the Software with a program that collects the necessary data directly from the html files.

To use this generator, download the latest JAR file, navigate a terminal to the jar file. Let me explain its use briefly:

``java -jar tipuesearchScraper-DATE.jar`` to execute the jar.

The actual jar needs 6 arguments, the first 4 are css selectors for the title of each page, the short description shown and the actual content we want to search through. The last 2 descript where the files are and where the script should write the data. As a whole, a search whould look like:


``java -jar tipuesearchScraper-DATE.jar "title" "meta[name=description]" "#content" ".tag" ./ ./tipuesearch/tipuesearch_content_new.js``

testhtml.html: 
```
<html>
<head><title>my Title</title><meta name=„description“ content=„Some description stuff“/></head>
<body> some text <div id=„content“> my content </div> <div class=„tag“>mytag</div> <div class=„tag“>another tag</div></body>
</html>
```
would generate 

tipuesearch_content_new.js:
```
var tipuesearch = {"pages": [{"tags“:“mytag anothertag","text":"Some description stuff -  my content","title“:“my Title","loc":"testhtml.html“}};
```
# License & Copyrights

http://Netzhaft.de (c) 2014. Tipque Search Generator is published under the MIT License.