package A1.Apteka.Apteka.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoice_id;
    @Column
    private String docType;
    @Column
    private String docName;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] fileByte;

    public Invoice(String docType, String docName, User user, byte[] fileByte) {
        this.docType = docType;
        this.docName = docName;
        this.user = user;
        this.fileByte = fileByte;
    }
}
