# FreeD4j
_FreeD4j_ is a Java library for parsing and receiving Free-D camera tracking data. It is currently **work in progress**.

## What is Free-D?
Free-D is an old, but widely-used protocol for exchanging live camera tracking data. You can find [more information here](https://www.manualsdir.com/manuals/641433/vinten-radamec-free-d.html),
but please let me know if you find any more resources! They seem to be few and far between.

## Usage
### Requirements
- Java 17
### Setup
1) Add the following Maven repository to your project: `https://mvn.spnr.dev/releases`
2) Add the dependency: `implementation("dev.spnr:freed4j:VERSION")` (where "VERSION" is the latest version, you'll find that in `build.gradle.kts` here)
### Developing
For now, it is up to you to create a UDP server. This is planned to change very soon.  
When you have a payload to parse, simply use the `FreeD4j#readMessage` method like so:
```java
byte[] payload;
// ...

Message message = FreeD4j.readMessage(payload); // Parse the data
switch (message) { // If you're using Java 21, you can use the new pattern switching!
  case PositionMessage positionMessage -> {
    int camera = positionMessage.camera();
    int x = positionMessage.x();
    int y = positionMessage.y();
    int z = positionMessage.z();
  }
}

// ...
```
### Quirks
- At the moment, units are given as the raw (usually signed) integers that they were parsed as. It is planned for an easy way to convert them to metric, but until then, you should read the
  document linked earlier for more info. There will be no support for imperial units.


## Related Projects
- https://github.com/stvmyr/freeD - A simple freeD tracking protocol implementation written in golang
- https://github.com/ssav7912/rust-FreeD - Library of types for use with the free-d Protocol