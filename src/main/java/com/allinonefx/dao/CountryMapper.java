package com.allinonefx.dao;

import com.allinonefx.model.Country;
import com.allinonefx.model.CountryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CountryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Fri Jan 19 10:20:02 CET 2018
     */
    long countByExample(CountryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Fri Jan 19 10:20:02 CET 2018
     */
    int deleteByExample(CountryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Fri Jan 19 10:20:02 CET 2018
     */
    int deleteByPrimaryKey(Short country_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Fri Jan 19 10:20:02 CET 2018
     */
    int insert(Country record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Fri Jan 19 10:20:02 CET 2018
     */
    int insertSelective(Country record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Fri Jan 19 10:20:02 CET 2018
     */
    List<Country> selectByExample(CountryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Fri Jan 19 10:20:02 CET 2018
     */
    Country selectByPrimaryKey(Short country_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Fri Jan 19 10:20:02 CET 2018
     */
    int updateByExampleSelective(@Param("record") Country record, @Param("example") CountryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Fri Jan 19 10:20:02 CET 2018
     */
    int updateByExample(@Param("record") Country record, @Param("example") CountryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Fri Jan 19 10:20:02 CET 2018
     */
    int updateByPrimaryKeySelective(Country record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Fri Jan 19 10:20:02 CET 2018
     */
    int updateByPrimaryKey(Country record);
}