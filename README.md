![FMX](https://github.com/kasisoft/plex/workflows/PLEX/badge.svg?branch=master&event=push)

# Purpose

This library provides convenient functionalities to extract data from excel files.


# Infos

* [daniel.kasmeroglu@kasisoft.com](mailto:daniel.kasmeroglu@kasisoft.com)
* [Github](https://github.com/kasisoft/plex/issues)
* [GIT](https://github.com/kasisoft/plex.git)


# Development Setup

The build uses Maven

* [https://maven.apache.org/](https://maven.apache.org/)


## Requirements

* Java 8


## Maven

### Releases

     <dependency>
         <groupId>com.kasisoft</groupId>
         <artifactId>com.kasisoft.libs.plex</artifactId>
         <version>0.2</version>
     </dependency>


### Snapshots

Snapshots can be used while accessing a dedicated maven repository. Your POM needs the following settings:

     <dependency>
         <groupId>com.kasisoft</groupId>
         <artifactId>com.kasisoft.libs.plex</artifactId>
         <version>0.3-SNAPSHOT</version>
     </dependency>
     
     <repositories>
         <repository>
             <id>github-plex</id>
             <url>https://maven.pkg.github.com/kasisoft/plex</url>
             <releases>
                 <enabled>false</enabled>
             </releases>
             <snapshots>
                 <enabled>true</enabled>
             </snapshots>
         </repository>
     </repositories>

You need to provide credentials in your _~/.m2/settings.xml_:

     <server>
         <id>github-plex</id>
         <username>username</username>
         <password>token</password>
     </server>

You can create a token with your github account:

* Open your Github settings
* Select _Developer settings_
* Select _Personal access tokens_
* Create a new token with read:package access



# License

MIT License

Copyright (c) 2021 Daniel Kasmeroglu (Kasisoft)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
