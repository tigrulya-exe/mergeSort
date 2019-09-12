# Merge Sort
Small program for sorting several already sorted (or partially sorted) files into supplied out file using merge sort (https://en.wikipedia.org/wiki/Merge_sort).

Actual jar file:
https://drive.google.com/file/d/12FHgBPwuQvzRR8AaJaPmUg3pPE_P8mZW

You can create a jar file yourself or download actual version from link above.

# Options

__-s__ or __-i__ - file content type (string or integer, required).<br/>
__-a__ or __-d__ - ascending or descending sort mode (default : __-a__).<br/>


# Program usage examples
For integers ascending:
```console
java –jar sort-it.jar -i -a out.txt in.txt
```

For strings descending:
```console
java –jar sort-it.jar -d -s out.txt in1.txt in2.txt
```

For strings ascending:
```console
java –jar sort-it.jar -s out.txt in1.txt in2.txt in3.txt
```
