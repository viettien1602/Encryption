Some common encrypting algorithm source code:
- HashingEncrypter: MD1, MD5, SHA1, SHA224, SHA256, SHA384, SHA512
- My_DES_AES: DES and AES symmetric algorithm
- MySymmetricEncrypter: 
    + CEASAR algorithm 
    + A complex algorithm based on CEASAR: 
        **First char: shift n postion (n: user define)
        **From second char to the end: shift x position (x is the Unicode decimal index of previous char) 