package servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "MyServlet",
        urlPatterns = {"/hello"}
    )
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //metrics for alert: TomcatDemoJvmGc
        String jvm_gc_collection_seconds =
                "# HELP jvm_gc_collection_seconds Time spent in a given JVM garbage collector in seconds.\n"
                + "# TYPE jvm_gc_collection_seconds summary\n"
                + "jvm_gc_collection_seconds_count{gc=\"PS MarkSweep\"} .932\n"
                + "jvm_gc_collection_seconds_sum{gc=\"PS MarkSweep\"} 4\n";
        
        //metrics for alert: TomcatDemoJvmMemory
        String jvm_memory_bytes_used = 
            "# HELP jvm_memory_bytes_used An estimate of the memory that the Java virtual machine is using for this pool\n"
            + "# TYPE jvm_memory_bytes gauge\n"
            + "jvm_memory_bytes_used{area=\"heap\"} 81920.0\n"
            + "jvm_memory_bytes_used{area=\"nonheap\"} 324.5\n";
            
        String jvm_memory_bytes_max =
            "# HELP jvm_memory_bytes_max The maximum amount of memory in bytes that can be used for memory management\n"
            + "# TYPE jvm_memory_bytes_max gauge\n"
            + "jvm_memory_bytes_max{area=\"heap\",id=\"PS Survivor Space\"} 41504.2\n"
            + "jvm_memory_bytes_max{area=\"heap\",id=\"PS Old Gen\"} 12310.5\n"
            + "jvm_memory_bytes_max{area=\"heap\",id=\"PS EdenSpace\"} 37854.4\n"
            + "jvm_memory_bytes_max{area=\"nonheap\"} 994399.1\n";
            
        //metrics for alert: TomcatDemoProcessRestarts
        String process_start_time_seconds =
            "# HELP process_start_time_seconds start time since unix epoch in seconds\n"
            + "# TYPE process_start_time_seconds gauge\n"
            + "process_start_time_seconds 44\n";
        
        //metrics for alert: TomcatDemoProcessTime
        String servlet_request_seconds =
            "# HELP servlet_request_seconds The time taken fulfilling servlet requests\n"
            + "# TYPE servlet_request_seconds histogram\n"
            + "servlet_request_seconds_bucket{method=\"GET\",le=\"0.01\"} 1.0\n"
            + "servlet_request_seconds_bucket{method=\"GET\",le=\"0.05\"} 2.0\n"
            + "servlet_request_seconds_bucket{method=\"GET\",le=\"0.1\"} 2.0\n"
            + "servlet_request_seconds_bucket{method=\"GET\",le=\"0.25\"} 2.0\n"
            + "servlet_request_seconds_bucket{method=\"GET\",le=\"0.5\"} 3.0\n"
            + "servlet_request_seconds_bucket{method=\"GET\",le=\"1.0\"} 4.0\n"
            + "servlet_request_seconds_bucket{method=\"GET\",le=\"2.5\"} 4.0\n"
            + "servlet_request_seconds_bucket{method=\"GET\",le=\"5.0\"} 5.0\n"
            + "servlet_request_seconds_bucket{method=\"GET\",le=\"10.0\"} 28.0\n"
            + "servlet_request_seconds_bucket{method=\"GET\",le=\"30.0\"} 67.0\n"
            + "servlet_request_seconds_bucket{method=\"GET\",le=\"+Inf\"} 67.0\n";
        
        //metrics for alert: TomcatDemoRequestErrors
        String servlet_response_status_total =
            "# HELP servlet_response_status_total Number of requests for a given context and status code\n"
            + "# TYPE servlet_response_status_total gauge\n"
            + "servlet_response_status_total{status=\"200\"} 54\n"
            + "servlet_response_status_total{status=\"501\"} 2\n"
            + "servlet_response_status_total{status=\"504\"} 8\n";
        
        String output = jvm_gc_collection_seconds + jvm_memory_bytes_used + jvm_memory_bytes_max +
                process_start_time_seconds + servlet_request_seconds + servlet_response_status_total + "# EOF\n";       
        ServletOutputStream out = resp.getOutputStream();
        out.write(output.getBytes());
        out.flush();
        out.close();
    }

}
