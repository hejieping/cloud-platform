package com.cpf.mysql.dao.PO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;

/**
 * Created by jieping on 2018-04-21
 */
@Data
@Entity(name = "asset")
public class AssetPO {
    @Id
    private String id;
    private String atype;
    private String aid;
    private String aname;
    private Date atime;
    private String pid;
    private String hostname;
    private String ipaddr;
    @Lob
    private String property;

}
