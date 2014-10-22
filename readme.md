To use this generator, download the latest JAR file, navigate a terminal to the jar file. Let me explain its use briefly:

``java -jar tipuesearchScraper-DATE.jar`` to execute the jar.

The actual jar needs 6 arguments, the first 4 are css selectors for the title of each page, the short description shown and the actual content we want to search through. The last 2 descript where the files are and where the script should write the data. As a whole, a search whould look like:


java -jar tipuesearchScraper-DATE.jar "title" "meta[name=description]" "#content" ".tag" /Users/bady/Sites/appStrap2.5.1/theme/ /Users/bady/Sites/appStrap2.5.1/theme/tipuesearch/tipuesearch_content_new.js