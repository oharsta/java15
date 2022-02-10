package zilverline;

import com.fasterxml.jackson.annotation.JsonProperty;

record Person(@JsonProperty("displayName") String name, @JsonProperty("age") int age) {
}