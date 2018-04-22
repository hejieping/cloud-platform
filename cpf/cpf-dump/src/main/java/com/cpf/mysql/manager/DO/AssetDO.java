package com.cpf.mysql.manager.DO;

import lombok.Data;

import java.util.Date;

/**
 * Created by jieping on 2018-04-22
 */
@Data
public class AssetDO {
    private String id;
    private String atype;
    private String aid;
    private String aname;
    private Date atime;
    private String pid;
    private String hostname;
    private String ipaddr;
    private String property;
}
