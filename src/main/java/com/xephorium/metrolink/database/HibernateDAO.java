/*
  Christopher Cruzen                        LaunchCode CS50
  "HibernateDAO.java"                            05.30.2015

  Problem Set #7

    This class contains a concrete set of database access
  methods which give the MetrolinkDAO interface form.

*/
package com.xephorium.metrolink.database;

import com.xephorium.metrolink.database.recordORM.*;
import com.xephorium.metrolink.database.recordORM.Time;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository("databaseReader")
public class HibernateDAO implements MetrolinkDAO
{
    /*--- Fields ---*/

    @Autowired
    private SessionFactory sessionFactory;

    /*--- Methods ---*/

    public ArrayList<Station> getStations()
    {
        sessionFactory.getCurrentSession().beginTransaction();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Station.class);
        // List list = criteria.list(); // ArrayList Conversion Issue
        sessionFactory.getCurrentSession().getTransaction().commit();
        return new ArrayList<Station>();
    }

    public ArrayList<Time> getStationArrivals(int stationID)
    {
        sessionFactory.getCurrentSession().beginTransaction();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Time.class);
        criteria.add(Restrictions.eq("stop_id", stationID));
        // List list = criteria.list(); // ArrayList Conversion Issue
        sessionFactory.getCurrentSession().getTransaction().commit();
        return new ArrayList<Time>();
    }


}
