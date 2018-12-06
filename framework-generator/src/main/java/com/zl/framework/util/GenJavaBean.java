package com.zl.framework.util;


import com.google.common.base.CaseFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * @desc: JavaBean生成
 * @author: "+author+"
 * @createTime: 2018年10月25日 上午10:55:14
 * @version: v1.0
 */
public class GenJavaBean {
    private Logger log = LoggerFactory.getLogger(getClass());
//    private final String mapperUrl = "com.zl.framework.mapper";
    private final String mapperUrl = "com.huobi.contract.risk.mapper";
//    private final String entityUrl = "com.zl.framework.entity";
    private final String entityUrl = "com.huobi.contract.common.entity";
//    private final String serviceUrl = "com.huobi.contract.common.entity";
    private final String serviceUrl = "com.huobi.contract.risk.service";
    private final String fileUrl = "/Users/zhangliang0211/Downloads/manager";//mac环境用/ win环境用\\
//    private final String fileUrl = "/Library/huobi/private-code/framework/framework-generator/src/main/java/com/zl/framework";
    private final String xmlUrl = "/Users/zhangliang0211/Downloads/manager";
//    private final String xmlUrl = "/Library/huobi/private-code/framework/framework-generator/src/main/resources";
    private final String pattern = "yyyy-MM-dd HH:mm:ss";
    private final String database = "mac-test3";
    private final String jdbcURL = "jdbc:mysql://127.0.0.1:3306/information_schema?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF-8";
    private final String user = "root";
    private final String password = "root";
    private final String driverClass = "com.mysql.jdbc.Driver";
    private final String author = "zhangliang";
    class TmpTable {
        public String tableid;
        public String tablename;
        public String columnid;
        public String columnname;
        public String datatype;
        public String lens;
        public String precision;
        public String scale;
        public String isnull;

        public TmpTable(String tableid, String tablename, String columnid, String columnname, String datatype,
                        String lens, String precision, String scale, String isnull) {
            super();
            this.tableid = tableid;
            this.tablename = tablename;
            this.columnid = columnid;
            this.columnname = columnname;
            this.datatype = datatype;
            this.lens = lens;
            this.precision = precision;
            this.scale = scale;
            this.isnull = isnull;
        }
    }

    public Map<String, ArrayList<TmpTable>> tablemap = new HashMap<String, ArrayList<TmpTable>>();

    public void writeLogfile(String filename, String str) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                f.createNewFile();
            }
            BufferedWriter out = null;
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename, true), "UTF-8"));
            out.write(str);
            out.write("\r\n");
            out.close();
        } catch (Exception e) {
        }
    }

    public void delfile(String filename) {
        File f = new File(filename);
        if (f.exists()) {
            f.delete();
        }
    }

    public void getEntityData() {
        tablemap.clear();
        Statement psts = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            Class.forName(driverClass);

            try {
                conn = DriverManager.getConnection(jdbcURL, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                psts = conn.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                rs = psts.executeQuery("select a.TABLE_NAME,b.TABLE_COMMENT,a.COLUMN_NAME,a.COLUMN_COMMENT,"
                        + " a.DATA_TYPE,a.CHARACTER_MAXIMUM_LENGTH,a.NUMERIC_PRECISION,a.NUMERIC_SCALE,a.IS_NULLABLE,a.ORDINAL_POSITION "
                        + " from COLUMNS a ,TABLES b "
                        + " where a.TABLE_SCHEMA='"+database+"' and a.TABLE_SCHEMA=b.TABLE_SCHEMA and a.TABLE_NAME=b.TABLE_NAME  "
                        + " order by a.TABLE_NAME,a.ORDINAL_POSITION");
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (rs.next()) {
                String tableid = "";
                String tablename = "";
                String columnid = "";
                String columnname = "";
                String datatype = "";
                String lens = "";
                String precision = "";
                String scale = "";
                String isnull = "";
                if (!PsUtil.isNullOrBlank(rs.getString(1))) {
                    tableid = rs.getString(1);
                }
                if (!PsUtil.isNullOrBlank(rs.getString(2))) {
                    tablename = rs.getString(2);
                }
                if (!PsUtil.isNullOrBlank(rs.getString(3))) {
                    columnid = rs.getString(3);
                }
                if (!PsUtil.isNullOrBlank(rs.getString(4))) {
                    columnname = rs.getString(4);
                }
                if (!PsUtil.isNullOrBlank(rs.getString(5))) {
                    datatype = rs.getString(5);
                }
                if (!PsUtil.isNullOrBlank(rs.getString(6))) {
                    lens = rs.getString(6);
                }
                if (!PsUtil.isNullOrBlank(rs.getString(7))) {
                    precision = rs.getString(7);
                }
                if (!PsUtil.isNullOrBlank(rs.getString(8))) {
                    scale = rs.getString(8);
                }
                if (!PsUtil.isNullOrBlank(rs.getString(9))) {
                    isnull = rs.getString(9);
                }

                ArrayList<TmpTable> tablelist = null;
                TmpTable tmp = new TmpTable(tableid, tablename, columnid, columnname, datatype, lens, precision, scale, isnull);
                if (tablemap.containsKey(tableid)) {
                    tablelist = tablemap.get(tableid);
                } else {
                    tablelist = new ArrayList<TmpTable>();
                    tablemap.put(tableid, tablelist);
                }
                tablelist.add(tmp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据处理错误 ！");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (psts != null) {
                    psts.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                log.info("数据库链接错误 ！");
            }
        }
        genJavaBean();
    }

    public void genJavaBean() {
        Iterator<?> iter = tablemap.entrySet().iterator();
        while (iter.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, ArrayList<TmpTable>> entry = (Entry<String, ArrayList<TmpTable>>) iter.next();
            ArrayList<TmpTable> list = entry.getValue();
            StringBuilder sb = new StringBuilder();
            String table = "";
            String tableTmp = "";
            String className = "";
            String hdtmp = "package " + entityUrl + ";\n\n"
                    + "import java.io.Serializable;\n";

            StringBuilder getsetstr = new StringBuilder();
            getsetstr.append("	public Long getId() {\n"
                    + "		return id;\n"
                    + "	}\n\n"


                    + "	public void setId(Long id) {\n"
                    + "		this.id = id;\n"
                    + "	}\n\n");

            String maperxmlCols = "";
            String resultMap = "";
            Map<String, String> impmap = new HashMap<String, String>();

            //mapper 中 使用的参数或条件
            Map<String, String> mapperss = new HashMap<String, String>();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    String datatype = list.get(i).datatype;
                    String tmpdatatype = "";


                    String column = list.get(i).columnid.toLowerCase();
                    System.out.println("old is ..... " + column);
                    //查找"_" 所在位置
                    String columnsss = "";
                    if (column.contains("_")) {
                        columnsss = column.substring(0, column.indexOf("_")) + column.substring(column.indexOf("_") + 1, column.indexOf("_") + 2).toUpperCase() + column.substring(column.indexOf("_") + 2, column.length());
                        while (columnsss.contains("_")) {
                            columnsss = columnsss.substring(0, columnsss.indexOf("_")) + columnsss.substring(columnsss.indexOf("_") + 1, columnsss.indexOf("_") + 2).toUpperCase() + columnsss.substring(columnsss.indexOf("_") + 2, columnsss.length());
                        }
                        column = columnsss;
                    }

                    System.out.println("new  is ..... " + column);

                    if (i == 0) {
                        maperxmlCols = maperxmlCols + "    " + list.get(i).columnid + ",";
                    } else {
                        maperxmlCols = maperxmlCols + list.get(i).columnid + ",";
                    }
                    String isnull = list.get(i).isnull;
                    if (i == 0) {
                        tableTmp = list.get(i).tableid;
                        className = list.get(i).tablename;
                        table = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, (list.get(i).tableid.replaceFirst("t_", "")));
                        sb.append("/**\n"
                                + "* @desc: " + list.get(i).tablename + "\n"
                                + "* @author: "+author+"\n"
                                + "* @createTime: "+new SimpleDateFormat(pattern).format(new Date())+"\n"
                                + "* @version: v1.0\n"
                                + "*/\n"
                                + "public class " + table + " implements Serializable{\n\n"
                                + "	private static final long serialVersionUID = 1L;\n\n"
                        );
                        sb.append("	private Long id;\n\n");

                        resultMap = resultMap + "    <id column=\"id\" property=\"id\" jdbcType=\"BIGINT\" />\n";

                        mapperss.put("id@id", "#{id,jdbcType=BIGINT}");
                    } else {
                        /**
                         *
                         */
                        sb.append("	/**\n"
                                + "    * @desc: " + list.get(i).columnname + "\n"
                                + "    * @author: "+author+"\n"
                                + "    */\n");
                        if ("decimal".equalsIgnoreCase(datatype)) {
                            tmpdatatype = "BigDecimal";
                            if (!impmap.containsKey("decimal")) {
                                impmap.put("decimal", "import java.math.BigDecimal;\n");
                            }
                            sb.append("	private BigDecimal " + column + ";\n\n");

                        } else if ("date".equalsIgnoreCase(datatype) || "datetime".equalsIgnoreCase(datatype)) {
                            tmpdatatype = "Date";
                            if (!impmap.containsKey("date")) {
                                impmap.put("date", "import java.util.Date;\n");
                            }
                            sb.append("	private Date " + column + ";\n\n");
                        } else if ("bigint".equalsIgnoreCase(datatype)) {
                            tmpdatatype = "Long";
                            sb.append("	private Long " + column + ";\n\n");
                        } else if ("int".equalsIgnoreCase(datatype)) {
                            tmpdatatype = "Integer";
                            sb.append("	private Integer " + column + ";\n\n");
                        } else if ("longtext".equalsIgnoreCase(datatype)) {
                            tmpdatatype = "String";
                            sb.append("	private String " + column + ";\n\n");
                        } else if ("text".equalsIgnoreCase(datatype)) {
                            tmpdatatype = "String";
                            sb.append("	private String " + column + ";\n\n");
                        } else if ("tinyint".equalsIgnoreCase(datatype)) {
                            tmpdatatype = "Integer";
                            sb.append("	private Integer " + column + ";\n\n");
                        } else if ("float".equalsIgnoreCase(datatype)) {
                            tmpdatatype = "float";
                            sb.append("	private float " + column + ";\n\n");
                        } else if ("varchar".equalsIgnoreCase(datatype) || "char".equalsIgnoreCase(datatype)) {
                            tmpdatatype = "String";
                            sb.append("	private String " + column + ";\n\n");

                        }
                        String columntmp = column.substring(0, 1).toUpperCase() + column.substring(1);

                        getsetstr.append("	public " + tmpdatatype + " get" + columntmp + "() {\n"
                                + "		return " + column + ";\n"
                                + "	}\n\n"
                                + "	public void set" + columntmp + "(" + tmpdatatype + " " + column + ") {\n"
                                + "		this." + column + " = " + column + ";\n"
                                + "	}\n\n");

                        if ("int".equalsIgnoreCase(datatype)) {
                            resultMap = resultMap + "    <result column=\"" + list.get(i).columnid + "\" property=\"" + column + "\" jdbcType=\"INTEGER\" />\n";
                            mapperss.put(list.get(i).columnid + "@" + column, "#{" + column + ", jdbcType=INTEGER}");
                        } else if("datetime".equalsIgnoreCase(datatype)){
                            resultMap = resultMap + "    <result column=\"" + list.get(i).columnid + "\" property=\"" + column + "\" />\n";
                            mapperss.put(list.get(i).columnid + "@" + column, "#{" + column + "}");
                        } else {
                            resultMap = resultMap + "    <result column=\"" + list.get(i).columnid + "\" property=\"" + column + "\" jdbcType=\"" + datatype.toUpperCase() + "\" />\n";
                            mapperss.put(list.get(i).columnid + "@" + column, "#{" + column + ", jdbcType=" + datatype.toUpperCase() + "}");
                        }

                    }
                }
                //实体类
                sb.append(getsetstr).append("}");
                String fname = fileUrl + "/entity/" + table + ".java";

                delfile(fname);
                Iterator<?> iterimp = impmap.entrySet().iterator();
                while (iterimp.hasNext()) {
                    @SuppressWarnings("unchecked")
                    Map.Entry<String, String> entryimp = (Entry<String, String>) iterimp.next();
                    String impstr = entryimp.getValue();
                    hdtmp = hdtmp + impstr;
                }
                writeLogfile(fname, hdtmp + "\n" + sb.toString());

                //mapper.java

                fname = fileUrl + "/mapper/" + table + "Mapper.java";
                delfile(fname);
                String mapperStr = "package " + mapperUrl + ";\n\n"
                        + "import java.util.List;\n"
                        + "import " + entityUrl + "." + table + ";\n"

                        + "/**\n"
                        + "* @desc: " + className + "持久层访问 接口  \n"
                        + "* @author: "+author+"\n"
                        + "* @createTime: "+new SimpleDateFormat(pattern).format(new Date())+"\n"
                        + "* @version: v1.0\n"
                        + "*/\n\n"


                        + "public interface " + table + "Mapper {\n\n "
                        + "    /**\n"
                        + "    * 查询所有" + className + "的信息   \n"
                        + "    * @desc:  查询所有" + className + "的信息 接口   \n"
                        + "    * @author: "+author+"\n"
                        + "    * @return :返回" + className + "的信息\n"
                        + "    * @createTime: "+new SimpleDateFormat(pattern).format(new Date())+"\n"
                        + "    */\n"
                        + "    List<" + table + "> listAll" + table + "();\n"
                        + "\n\n"
                        + "    /**\n"
                        + "    * 根据属性条件查询" + className + "的信息   \n"
                        + "    * @desc:  根据属性条件查询" + className + "的信息 接口  \n"
                        + "    * @author: "+author+"\n"
                        + "    * @param record :" + className + "的属性参数 \n"
                        + "    * @return : 返回" + className + "的信息\n"
                        + "    * @createTime: "+new SimpleDateFormat(pattern).format(new Date())+"\n"
                        + "    */\n"
                        + "    List<" + table + "> list" + table + "ByCondition(" + table + " record);\n"
                        + "\n\n"
                        + "    /**\n"
                        + "    * 新增" + className + "的信息   \n"
                        + "    * @desc:  新增" + className + "的信息  接口  \n"
                        + "    * @author: "+author+"\n"
                        + "    * @param record :" + className + "的实体类 \n"
                        + "    * @return : 返回插入状态信息\n"
                        + "    * @createTime: "+new SimpleDateFormat(pattern).format(new Date())+"\n"
                        + "    */\n"
                        + "    int insert" + table + "(" + table + " record);\n"
                        + "\n\n"
                        + "    /**\n"
                        + "    * 根据PrimaryKey修改" + className + "的信息   \n"
                        + "    * @desc:  根据PrimaryKey修改" + className + "的信息  接口  \n"
                        + "    * @author: "+author+"\n"
                        + "    * @param record :" + className + "的实体类 \n"
                        + "    * @return : 返回修改状态信息\n"
                        + "    * @createTime: "+new SimpleDateFormat(pattern).format(new Date())+"\n"
                        + "    */\n"
                        + "    int update" + table + "ByPrimaryKey(" + table + " record);\n"
                        + "\n\n"
                        + "    /**\n"
                        + "    * 根据PrimaryKey删除" + className + "的信息   \n"
                        + "    * @desc:  根据PrimaryKey删除" + className + "的信息  接口  \n"
                        + "    * @author: "+author+"\n"
                        + "    * @param id : 主键数值 \n"
                        + "    * @return : 返回删除状态信息\n"
                        + "    * @createTime: "+new SimpleDateFormat(pattern).format(new Date())+"\n"
                        + "    */\n"
                        + "    int delete" + table + "ByPrimaryKey(Long id);\n"
                        + "}\n\n";

                writeLogfile(fname, mapperStr);
                
                //service
                fname = fileUrl + "/service/" + table + "Service.java";
                delfile(fname);
                String serviceStr = "package "+serviceUrl+";\n\n"
                        + "/**\n"
                        + " * @desc: "+className+" service \n"
                        + "* @author: "+author+"\n"
                        + "* @createTime: "+new SimpleDateFormat(pattern).format(new Date())+"\n"
                        + "* @version: v1.0\n"
                        + "*/\n\n"

                        +"public interface "+table+"Service {\n\n"
                        +"}\n\n";
                writeLogfile(fname, serviceStr);
                //mapper.xml
                fname = xmlUrl+"/mapper/" + table + "Mapper.xml";
                delfile(fname);

                Iterator<Entry<String, String>> mappers = mapperss.entrySet().iterator();
                String whereCon = "";
                String insertKey = "";
                String insertvalue = "";
                String updatevalue = "   ";
                while (mappers.hasNext()) {

                    Map.Entry<String, String> entryimp = (Entry<String, String>) mappers.next();
                    String impKey = entryimp.getKey();
                    String[] arr = impKey.split("@");
                    String impstr = entryimp.getValue();
                    insertKey = insertKey + arr[0] + ", ";
                    insertvalue = insertvalue + impstr + ", ";
                    if (!"id".equals(arr[0])) {
                        updatevalue = updatevalue + "        " + arr[0] + " = " + impstr + ",\n ";
                    }
                    whereCon = whereCon + "      <if test=\"" + arr[1] + " != null and " + arr[1] + " !='' \">\n"
                            + "        AND " + arr[0] + " = " + impstr + "  \n"
                            + "      </if> \n";

                }
                insertKey = insertKey.substring(0, insertKey.lastIndexOf(","));
                insertvalue = insertvalue.substring(0, insertvalue.lastIndexOf(","));
                updatevalue = updatevalue.substring(0, updatevalue.lastIndexOf(","));

                mapperStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                        + "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n"
                        + "<mapper namespace=\"" + mapperUrl + "." + table + "Mapper\" >\n"
                        + "  <resultMap id=\"baseResultMap\" type=\"" + entityUrl + "." + table + "\" >\n"
                        + resultMap
                        + "  </resultMap>\n"
                        + "  <sql id=\"Base_Column_List\" >\n"
                        + "" + maperxmlCols.substring(0, maperxmlCols.length() - 1) + "\n"
                        + "  </sql>\n"
                        + "  <!-- 查询所有" + className + "的信息   -->\n"
                        + "  <select id=\"listAll" + table + "\" resultMap=\"baseResultMap\">\n"
                        + "    select \n"
                        + "    <include refid=\"Base_Column_List\" /> \n"
                        + "    from " + tableTmp + " \n"
                        + "  </select>\n"
                        + "  <!-- 根据属性条件查询" + className + "的信息   -->\n"
                        + "  <select id=\"list" + table + "ByCondition\" parameterType=\"map\"  resultMap=\"baseResultMap\">\n"
                        + "    select \n"
                        + "    <include refid=\"Base_Column_List\" /> \n"
                        + "    from " + tableTmp + " \n"
                        + "    <where> 1=1  \n"
                        + whereCon
					    
					/*    <if test="userId != null and userId !='' ">
					  	AND 	otc.user_id = #{userId,jdbcType=VARCHAR} 
					  </if>*/

                        + "   </where>\n"
                        + "  </select>\n"

                        + "  <!-- 新增" + className + "的信息  -->\n"
                        + "  <insert id=\"insert" + table + "\" parameterType=\"" + entityUrl + "." + table + "\">\n"
                        + "    insert into " + tableTmp + " ( " + insertKey + ")\n"
                        + "    values ( " + insertvalue + ") \n"
                        + "  </insert>\n"

                        + "  <!--  根据PrimaryKey修改" + className + "的信息 -->\n"
                        + "  <update id=\"update" + table + "ByPrimaryKey\" parameterType=\"" + entityUrl + "." + table + "\">\n"
                        + "    update " + tableTmp + "  \n"
                        + "    set " + updatevalue + "  \n"
                        + "    where id = #{id,jdbcType=BIGINT} \n"
                        + "  </update>\n"

                        + "  <!--   根据PrimaryKey删除" + className + "的信息-->\n"
                        + "  <delete id=\"delete" + table + "ByPrimaryKey\" parameterType=\"java.lang.Long\">\n"
                        + "    delete from " + tableTmp + "  \n"
                        + "    where id = #{id,jdbcType=BIGINT} \n"
                        + "  </delete>\n"

                        + "</mapper>\n";
                writeLogfile(fname, mapperStr);

            }
        }
    }

    public static void main(String[] args) {
        GenJavaBean gbean = new GenJavaBean();
        gbean.getEntityData();
        String aa = "wo ai {0} {1}";
        String success = MessageFormat.format(aa, "hello", "world");
        System.out.println(success + "success");
    }
}

