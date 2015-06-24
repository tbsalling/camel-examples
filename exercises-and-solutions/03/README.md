1 - Built-in converter
---
Use a built-in converter to convert Exchanges containing Twitter statuses to Strings before they are sent to stream:out.

2 - Create a custom converter
---
Use a customer converter to converter twitter4j.Status to a custom POJO containing Twitter text and source and a hash value of the entire tweet.
Stream the POJO's to stream:out

3 - Use a transformer
---
Use a transformer to prepend Twitter status texts with "--> " before they are sent to stream:out

