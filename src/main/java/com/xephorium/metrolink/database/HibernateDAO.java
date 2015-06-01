/*
  Christopher Cruzen                        LaunchCode CS50
  "HibernateDAO.java"                            05.30.2015

  Problem Set #7

    This class contains a concrete set of database access
  methods which give the MetrolinkDAO interface form.

*/
package com.xephorium.metrolink.database;

import com.xephorium.metrolink.database.record.*;
import com.xephorium.metrolink.database.record.Time;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

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
        criteria.add(Restrictions.like("name", "METROLINK STATION", MatchMode.ANYWHERE));
        @SuppressWarnings("unchecked")
        List<Station> list = criteria.list();
        sessionFactory.getCurrentSession().getTransaction().commit();

        return new ArrayList<Station>(list);
    }

    public ArrayList<Time> getStationArrivals(int stationID)
    {
        sessionFactory.getCurrentSession().beginTransaction();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Arrival.class);
        criteria.add(Restrictions.eq("stop_id", stationID));
        @SuppressWarnings("unchecked")
        List<Arrival> list = criteria.list();
        sessionFactory.getCurrentSession().getTransaction().commit();

        return convertArrivals(list);
    }

    private ArrayList<Time> convertArrivals(List<Arrival> arrivals)
    {
        ArrayList<Time> times = new ArrayList<Time>();
        for(Arrival ar: arrivals)
            times.add(Time.convertString(ar.getTime()));
        return times;
    }


}
