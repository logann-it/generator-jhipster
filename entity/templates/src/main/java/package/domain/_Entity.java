package <%=packageName%>.domain;
<% if (relationships.length > 0  && (fieldsContainOwnerManyToMany == false || fieldsContainOneToMany == true)) { %>
import com.fasterxml.jackson.annotation.JsonIgnore;<% } %><% if (fieldsContainLocalDate == true) { %>
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import <%=packageName%>.domain.util.CustomLocalDateSerializer;<% } %><% if (hibernateCache != 'no') { %>
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;<% } %><% if (fieldsContainLocalDate == true) { %><% if (databaseType == 'sql') { %>
import org.hibernate.annotations.Type;<% } %>
import org.joda.time.LocalDate;<% } %><% if (databaseType == 'nosql') { %>
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;<% } %>
<% if (databaseType == 'sql') { %>
import javax.persistence.*;<% } %>
import java.io.Serializable;<% if (fieldsContainBigDecimal == true) { %>
import java.math.BigDecimal;<% } %><% if (relationships.length > 0) { %>
import java.util.HashSet;
import java.util.Set;<% } %>

/**
 * A <%= entityClass %>.
 */<% if (databaseType == 'sql') { %>
@Entity<% } %><% if (databaseType == 'sql' && hibernateCache != 'no') { %>
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)<% } %><% if (databaseType == 'nosql') { %>
@Document<% } %>
public class <%= entityClass %> extends AbstractAuditingEntity {
	
	private static final long serialVersionUID = <%= Math.floor(Math.random() * 0x10000000000000) %>L;
<% for (fieldId in fields) { %>
    private <%= fields[fieldId].fieldType %> <%= fields[fieldId].fieldName %>;
<% } %><% for (relationshipId in relationships) { %><% if (relationships[relationshipId].relationshipType == 'one-to-many') { %>
    @OneToMany(mappedBy = "<%= entityInstance %>")
    @JsonIgnore<% if (hibernateCache != 'no') { %>
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)<% } %>
    private Set<<%= relationships[relationshipId].otherEntityNameCapitalized %>> <%= relationships[relationshipId].otherEntityName %>s = new HashSet<>();<% } else if (relationships[relationshipId].relationshipType == 'many-to-one') { %>
    @ManyToOne
    private <%= relationships[relationshipId].otherEntityNameCapitalized %> <%= relationships[relationshipId].otherEntityName %>;<% } else if (relationships[relationshipId].relationshipType == 'many-to-many') { %>
    @ManyToMany<% if (relationships[relationshipId].ownerSide == false) { %>(mappedBy = "<%= entityInstance %>s")
    @JsonIgnore<% } %><% if (hibernateCache != 'no') { %>
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)<% } %>
    private Set<<%= relationships[relationshipId].otherEntityNameCapitalized %>> <%= relationships[relationshipId].otherEntityName %>s = new HashSet<>();<% } else { %>
    @OneToOne<% if (relationships[relationshipId].ownerSide == false) { %>(mappedBy = "<%= entityInstance %>")<% } %>
    private <%= relationships[relationshipId].otherEntityNameCapitalized %> <%= relationships[relationshipId].otherEntityName %>;<% } %>
<% } %><% for (fieldId in fields) { %>
    public <%= fields[fieldId].fieldType %> get<%= fields[fieldId].fieldNameCapitalized %>() {
        return <%= fields[fieldId].fieldName %>;
    }

    public void set<%= fields[fieldId].fieldNameCapitalized %>(<%= fields[fieldId].fieldType %> <%= fields[fieldId].fieldName %>) {
        this.<%= fields[fieldId].fieldName %> = <%= fields[fieldId].fieldName %>;
    }
<% } %><% for (relationshipId in relationships) { %><% if (relationships[relationshipId].relationshipType == 'one-to-many' || relationships[relationshipId].relationshipType == 'many-to-many') { %>
    public Set<<%= relationships[relationshipId].otherEntityNameCapitalized %>> get<%= relationships[relationshipId].otherEntityNameCapitalized %>s() {
        return <%= relationships[relationshipId].otherEntityName %>s;
    }

    public void set<%= relationships[relationshipId].otherEntityNameCapitalized %>s(Set<<%= relationships[relationshipId].otherEntityNameCapitalized %>> <%= relationships[relationshipId].otherEntityName %>s) {
        this.<%= relationships[relationshipId].otherEntityName %>s = <%= relationships[relationshipId].otherEntityName %>s;
    }<% } else { %>
    public <%= relationships[relationshipId].otherEntityNameCapitalized %> get<%= relationships[relationshipId].otherEntityNameCapitalized %>() {
        return <%= relationships[relationshipId].otherEntityName %>;
    }

    public void set<%= relationships[relationshipId].otherEntityNameCapitalized %>(<%= relationships[relationshipId].otherEntityNameCapitalized %> <%= relationships[relationshipId].otherEntityName %>) {
        this.<%= relationships[relationshipId].otherEntityName %> = <%= relationships[relationshipId].otherEntityName %>;
    }<% } %>
<% } %>
}
