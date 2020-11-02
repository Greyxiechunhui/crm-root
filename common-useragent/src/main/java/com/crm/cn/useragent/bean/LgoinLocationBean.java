package com.crm.cn.useragent.bean;

import java.util.Map;

public class LgoinLocationBean {
    /**
     * result : {"Isp":"内网 IP","Country":"","City":"内网IP","Province":""}
     * reason : 查询成功
     * error_code : 0
     * resultcode : 200
     */
    private Map<String,String> result;
    private String reason;
    private int error_code;
    private String resultcode;

    public void setResult(Map<String,String> result) {
        this.result = result;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public Map<String,String> getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }

    public int getError_code() {
        return error_code;
    }

    public String getResultcode() {
        return resultcode;
    }

    public class ResultEntity {
        /**
         * Isp : 内网 IP
         * Country :
         * City : 内网IP
         * Province :
         */
        private String Isp;
        private String Country;
        private String City;
        private String Province;

        public void setIsp(String Isp) {
            this.Isp = Isp;
        }

        public void setCountry(String Country) {
            this.Country = Country;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public String getIsp() {
            return Isp;
        }

        public String getCountry() {
            return Country;
        }

        public String getCity() {
            return City;
        }

        public String getProvince() {
            return Province;
        }
    }

    /*
    * 需要插件  这个插件的功能 就是通过json格式字符串 快速生成对象
    *
    * */


}
