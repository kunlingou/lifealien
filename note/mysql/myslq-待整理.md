### bianry

- INSERT INTO test_bin(bin_id) VALUES(UNHEX('FA34E10293CB42848573A4E39937F479')); 
- INSERT INTO test_bin(bin_id) VALUES(UNHEX(?)); 或 
- INSERT INTO test_bin(bin_id) VALUES(x'FA34E10293CB42848573A4E39937F479'); 

- SELECT HEX(bin_id) AS bin_id FROM test_bin;
- SELECT HEX(bin_id) AS bin_id FROM test_bin WHERE bin_id = UNHEX('FA34E10293CB42848573A4E39937F479'); 
- SELECT HEX(bin_id) AS bin_id FROM test_bin WHERE bin_id = UNHEX(?); 
- 
  SELECT HEX(bin_id) AS bin_id FROM test_bin WHERE bin_id = x'FA34E10293CB42848573A4E39937F479';   

- CREATE FUNCTION uu_id() RETURNS binary(16) RETURN UNHEX(REPLACE(UUID(),'-','')); 或
- CREATE FUNCTION uu_id() RETURNS binary(16) RETURN UNHEX(REVERSE(REPLACE(UUID(),'-',''))); 使用： INSERT INTO test_bin(bin_id) VALUES(uu_id()); 

## 公式

### 随机生成一个uuid

- replace(uuid(),'-','')

### HEX和UNHEX

- unhex("16进制字符串")
- hex("二进制")