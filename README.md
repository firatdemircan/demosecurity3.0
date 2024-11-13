- Spring security 6.1 with spring boot 3 above
- Jwt 0.11.5

Implemeted all th recessary code.
- jwt token
- role based security
- exception handling in security part 

you just need to give an db and rest is set.

-username = firat.demircan
-password = 123456 

with this you can get a token right away and test endpoints.

Explanation for new learner

--All request going throgh jwt filter, 
  - if jwt is happen to be in header and valid then the code will extract claims from token and  just fill UsernamePasswordAuthenticationToken and filter chain  will contiune. now the request can reach controller
  - if there is no jwt your request will look for controller endpoint and if its permitAll request will respond.
  - our login endpoint its public so anyone with right json body can come
  - Then in our login method will search authenticationprovider. That auth provider will look your credentials and give you a token

