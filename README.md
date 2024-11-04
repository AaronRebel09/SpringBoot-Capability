# SpringBoot-Capability

setup java jdk and set java_home, 
after install mvn locally finally run follow command 
mvn spring-boot:run

lab 01 : from a swagger json file aplly reverse engineering to retrieve java classes 

I launch following script to generate java classes 

java -jar swagger-codegen-cli.jar generate -i C:\\Users\\rodrigo.d.martinez\\Documents\\mentoring_accenture\\SpringBoot-Capability\\demo-users\\api-docs\\get_active_users.json --api-package com.example.genusers --model-package com.example.genusers.model --invoker-package com.example.genusers.client --group-id com.example --artifact-id gen-users --artifact-version 0.0.1-SNAPSHOT -l java --library feign -o C:\\Users\\rodrigo.d.martinez\\Documents\\mentoring_accenture\\SpringBoot-Capability\\codegen-utility\\code-gen\\
