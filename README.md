# MiZip-Tools
Calculate A/B keys (MiZip) for a Mifare tag.
> Based on: https://github.com/iceman1001/proxmark3/blob/master/client/scripts/calc_mizip.lua but rewritten in Java

To generate the keys use the tester *MiZipAllTester.java* or download the .jar from [here](https://github.com/ErikPelli/MiZipGen/releases), and insert your Mifare tag UID.
If you want to use the class as library, please consult the docs.

To run the .jar executable (tested with Java 12) use:
> java -jar MiZipGen.jar

Representative scheme of multidimensional array returned from genAllKeys:
```
{
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
}
```
