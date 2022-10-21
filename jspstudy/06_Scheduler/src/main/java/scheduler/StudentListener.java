package scheduler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/*
	ServletContextListener 인터페이스
	
	1. 웹 애플리케이션(프로젝트)의 LifeCycle을 가진다. (웹 애플리케이션과 함께 동작한다. 웹애 시작하면 리스너도 시작, 종료하면 리스너도 종료. 같은 범위를 가짐)
 	2. contextInitialized() : 웹 애플리케이션 시작할 때 동작
 	3. contextDestroyed()	: 웹 애플리케이션 종료할 때 동작

 */
@WebListener    // 나는 리스너입니다~!~!~!~!!!
public class StudentListener implements ServletContextListener {
	
	// field
	private Scheduler scheduler;   // 인터페이스 이름이 Scheduler. import하깅

	// constructor 	// 생성자에서는 스케줄러 필드를 만드는 작업을 할 거임
	// Scheduler scheduler 생성
    public StudentListener() {
        SchedulerFactory factory = null;
        try {
        	factory = new StdSchedulerFactory();   // 스탠다드스케줄러팩토리
        	scheduler = factory.getScheduler();	   // 스케줄러 시작
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }   // 리스너의 동작은 이 플젝을 실행하면(run as) -> 리스너가 동작 -> 제일 먼저 동작하는게 생성자(스케줄)가 만들어지는거 
    	// 즉, 리스너 동작하면 스케줄러 만들어짐 -> 만들어진 스케줄러에 job과 trigger 동작

	
    // contextDestroyed() 메소드
    // scheduler 종료
    public void contextDestroyed(ServletContextEvent arg0)  { 
         try {
        	 if(scheduler != null) {
        		 scheduler.shutdown();  // 스케줄러 종료
        	 }
         } catch (Exception e) {
        	 e.printStackTrace();
         }
    }

	// contextInitialized() 메소드
    // Job, Trigger 생성
    // scheduler에 Job, Trigger 등록  -> 언제, 무슨 작업을 수행한다!
    // scheduler 시작
    public void contextInitialized(ServletContextEvent arg0)  { 
        
    	/*
    		Cron Expression (크론식) - www.cronMaker.com 참고해 볼 것
    		
    		1. 구성(순서)
    			초 분 시 일 월 요일 [년도]
    			
    		2. 상세
    			1) 초 : 0 ~ 59
    			2) 분 : 0 ~ 59
    			3) 시 : 0 ~ 23
    			4) 일 : 1 ~ 31
    			5) 월 : 0 ~ 11 또는 JAN, FEB, MAR, APR, MAY, JUN, JUL, AUS, SEP, OCT, NOV, DEC
    	  		6) 요일 : 1(MON) ~ 7(SUN) 또는 MON, TUE, WED, THR, FRI, SAT, SUN
    	  		
    	  	3. 작성
    	  		1) * : 매번 (매월, 매일, 매요일 ...) - 특정 시간에 매번 동작함
    	  		2) ? : 설정 없음(일, 요일에서 사용)
    	  		3) / : 주기(동작 주기)
    	  				A/B : A부터 B마다 동작,   0/1 : 초에 세팅하면 1초마다 동작, 분에 세팅하면 1분마다, 시에 세팅하면 1시간마다
    	  	
    	  	4. 작성 예시
    	  		1) 매일 10초마다   : 0/10 * * * * ? (매월, 매일, 매시, 매분, 10초마다) ?는 신경쓰지말라고 생각하믄 됨
    	  		2) 매일 1분마다    : 0 0/1 * * * ?
    	  		3) 금요일 12시마다 : 0 0 12 ? * FRI   (12시 0분 0초, 며칠인지 모르니까 일은 지정할 수 없음. 4번이나 있음..)
    	  		4) 주말 12시마다 : 0 0 12 ? * SAT,SUN (12시 0분 0초, 토요일일요일마다)
    	*/
    	
    	
    	try {
    		
    		// Job 생성
    		JobDetail job = JobBuilder.newJob(StudentTop3Job.class)   // quatz의 interface, JobBuilder는 클래스   // class 자체를 등록 
    										// StudentTop3Job라는 Job의 클래스 타입 전달
    				.withIdentity("job1", "group1")
    				.build();    		
    		
    		// Trigger 생성
    		Trigger trigger = TriggerBuilder.newTrigger()
    				.withIdentity("trigger1", "group1")
    				.startNow()  // 바로 시작한다
    				.withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?"))  // 스케줄은 동작할 시간을 이야기 하는것 -> CronScheduleBuilder : 크론식을 이용한 빌더
    												// 30초마다. 월단위는 ?
    				.build();
    		
    		// scheduler에 Job과 Trigger 등록
    		scheduler.scheduleJob(job, trigger);
    		
    		// scheduler 실행
    		scheduler.start();    // start() - thread 동작. 스케줄러가 별도의 스레드로 동작해야 되기 때문에 start() 메소드 사용
    	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    }
	
}
