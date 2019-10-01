# MiZipKeyGen
Calculate A/B keys (MiZip) for a Mifare tag.
> Based on: https://github.com/iceman1001/proxmark3/blob/master/client/scripts/calc_mizip.lua

Tool written in Java, instead of Lua.

To generate the keys with the test program compile to bytecode & run MizipGen.java, and insert in the input your Mifare UID.
If you want to use the class, only public method is GenAB.gen(String uid) and it returns a two-dimensional array.
Returned array structure (JSON):
```
{
    "0":{
      "0": "A sector 0",
      "1": "A sector 1",
      "2": "A sector 2",
      "3": "A sector 3",
      "4": "A sector 4"
    },
    "1":{
      "0": "B sector 0",
      "1": "B sector 1",
      "2": "B sector 2",
      "3": "B sector 3",
      "4": "B sector 4"
    }
}
```
