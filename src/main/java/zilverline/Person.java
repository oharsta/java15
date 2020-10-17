package zilverline;

import com.fasterxml.jackson.annotation.JsonProperty;

record Person(@JsonProperty("name") String name,@JsonProperty("age") int age) {}