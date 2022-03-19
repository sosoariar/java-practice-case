package com.soso.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

   private DataSource dataSource;
   // key以 namespace+id 组成
   private Map<String,MappedStatement> mappedStatementMap = new HashMap<>();

   public DataSource getDataSource() {
      return dataSource;
   }

   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
   }

   public Map<String, MappedStatement> getMappedStatementMap() {
      return mappedStatementMap;
   }

   public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
      this.mappedStatementMap = mappedStatementMap;
   }
}
