# MiZip-Tools
Calculate A/B keys (MiZip) for a Mifare tag.
> Based on: https://github.com/iceman1001/proxmark3/blob/master/client/scripts/calc_mizip.lua but rewritten in Java

To generate the keys use the tester *MizipGenMultiTest.java*, and insert your Mifare tag UID.
If you want to use the class as library, view the javadoc.
Returned array structure from the class (as JSON):
```
{
    [
      "A sector 0",
      "A sector 1",
      "A sector 2",
      "A sector 3",
      "A sector 4"
    ],
    [
      "B sector 0",
      "B sector 1",
      "B sector 2",
      "B sector 3",
      "B sector 4"
    ]
}
```
