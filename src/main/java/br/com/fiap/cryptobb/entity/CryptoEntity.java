package br.com.fiap.cryptobb.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_CRYPTOS")
public class CryptoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String acronym;

    @Column
    private BigDecimal usdValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public BigDecimal getUsdValue() {
        return usdValue;
    }

    public void setUsdValue(BigDecimal usdValue) {
        this.usdValue = usdValue;
    }
}
