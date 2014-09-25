package <%=packageName%>.domain;
<% if (hibernateCache != 'no' && databaseType == 'sql') { %>
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;<% } %><% if (databaseType == 'nosql') { %>
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;<% } %>
<% if (databaseType == 'sql') { %>
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;<% } %>
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * An authority (a security role) used by Spring Security.
 */<% if (databaseType == 'sql') { %>
@Entity<% if (hibernateCache != 'no') { %>
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)<% } %><% } %><% if (databaseType == 'nosql') { %>
@Document<% } %>
public class Authority extends AbstractAuditingEntity {

	private static final long serialVersionUID = <%= Math.floor(Math.random() * 0x10000000000000) %>L;

	@NotNull
    @Size(min = 0, max = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
