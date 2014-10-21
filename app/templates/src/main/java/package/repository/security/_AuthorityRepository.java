package <%=packageName%>.repository.security;

import <%=packageName%>.domain.security.Authority;<% if (databaseType == 'sql') { %>
import org.springframework.data.jpa.repository.JpaRepository;<% } %><% if (databaseType == 'nosql') { %>
import org.springframework.data.mongodb.repository.MongoRepository;<% } %>

<% if (databaseType == 'sql') { %>/**
 * Spring Data JPA repository for the Authority entity.
 */<% } %><% if (databaseType == 'nosql') { %>/**
 * Spring Data MongoDB repository for the Authority entity.
 */<% } %>
public interface AuthorityRepository extends <% if (databaseType == 'sql') { %>JpaRepository<% } %><% if (databaseType == 'nosql') { %>MongoRepository<% } %><Authority, String> {
}
