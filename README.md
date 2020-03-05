# MiZip-Tools
Calculate A/B keys (MiZip) for a Mifare tag.
> Based on: https://github.com/iceman1001/proxmark3/blob/master/client/scripts/calc_mizip.lua but rewritten in Java

To generate the keys use the tester *MiZipAllTester.java* or download the .jar from [here](https://github.com/ErikPelli/MiZipGen/releases) (Made with OpenJDK 12), and insert your Mifare tag UID when required.
If you want to use the class as library, please consult the docs.

To create your .jar executable and use it type:
> jar --create --main-class=MiZipAllTester --file MiZipGen.jar MiZipAllTester.class MiZipGen.class

> java -jar MiZipGen.jar

Representative scheme of multidimensional array returned from genAllKeys:
```
[
    [
      "Key A sector 0",
      "Key B sector 0",
    ],
    [
      "Key A sector 1",
      "Key B sector 1",
    ],
    [
      "Key A sector 2",
      "Key B sector 2",
    ],
    [
      "Key A sector 3",
      "Key B sector 3",
    ],
    [
      "Key A sector 4",
      "Key B sector 4",
    ]
]
```
