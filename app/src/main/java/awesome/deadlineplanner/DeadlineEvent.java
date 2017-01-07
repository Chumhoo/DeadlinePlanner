package awesome.deadlineplanner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by caesa on 2017/1/6.
 */
/*TODO:事件名字
  TODO:事件描述
 *TODO:截止日期
 *TODO:重要程度
 *TODO:工作量评级
 *TODO:工作提示
 *TODO:是否完成
 */
public class DeadlineEvent {

    private long eventID;//只能在创建或者从数据库读出时由构造函数赋值，是第一次创建的时间
    private String eventName;//
    private String description;//
    private Calendar date;//
    private long importanceLevel;//
    private long predictTime;//
    private String tips;//
    private long finished;//这里是完成时间的从1970-01-01的毫秒数
    private long costTime;


    //第一次创建的构造函数,默认必须传入的是事件名，年，月，日，时，分
    public DeadlineEvent(String eventName, int year, int month, int day,int hour, int minute){
        eventID=Calendar.getInstance().getTimeInMillis();
        setEventName(eventName);
        setDescription("");
        setDate(year, month, day, hour,minute);
        setImportanceLevel(0);
        setPredictTime(0);
        setTips("");
        setFinished(0);
        setCostTime(0);
    }

    //从数据库读出来之后的构造函数
    public DeadlineEvent(long eventID,String eventName,String description,long deadline,long importanceLevel,long predictTime,String tips,long finished,long costTime){
        this.eventID=eventID;
        setEventName(eventName);
        setDescription(description);
        setDate(deadline);
        setImportanceLevel(importanceLevel);
        setPredictTime(predictTime);
        setTips(tips);
        setFinished(finished);
        setCostTime(costTime);
    }


    public long getEventID(){return eventID;}

    public void setEventName(String eventName){
        this.eventName=eventName;
    }
    public String getEventName(){return eventName;}

    public void setImportanceLevel(long importanceLevel){
        this.importanceLevel=importanceLevel;
    }
    public long getImportanceLevel(){return importanceLevel;}

    public void setTips(String tips){
        this.tips=tips;
    }
    public String getTips(){return tips;}

    public void setPredictTime(long time){predictTime=time;}
    public long getPredictTime(){return predictTime;}

    public void setDescription(String description){this.description=description;}
    public String getDescription(){return description;}

    //
    public void setFinished(long finished){this.finished=finished;}
    public long getFinished(){return finished;}

    //可以通过1970-01-01至今的毫秒数设置时间，也可以用年月日时分的形式设置，获取就获取毫秒数吧
    public void setDate(long time){date.setTimeInMillis(time);}
    public void setDate(int year, int month, int day,int hour, int minute){this.date.set(year, month, day, hour,minute);}
    public long getDate(){return date.getTimeInMillis();}

    public long getCostTime() {
        return costTime;
    }
    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

}
