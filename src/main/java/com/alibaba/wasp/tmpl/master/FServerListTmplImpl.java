// Autogenerated Jamon implementation
// /Users/jaywong/data/opensource/hadoop/wasp/trunk/src/main/jamon/./org/apache/wasp/tmpl/master/FServerListTmpl.jamon

package com.alibaba.wasp.tmpl.master;

// 27, 9
import java.util.*;
// 28, 9
import org.apache.hadoop.util.StringUtils;
// 29, 9
import org.apache.hadoop.hbase.util.Bytes;
// 30, 9
import org.apache.hadoop.hbase.util.JvmVersion;
// 31, 9
import org.apache.hadoop.hbase.util.FSUtils;
// 32, 9
import com.alibaba.wasp.master.FMaster;
// 33, 9
import org.apache.hadoop.hbase.HConstants;
// 34, 9
import com.alibaba.wasp.ServerLoad;
// 35, 9
import com.alibaba.wasp.ServerName;
// 36, 9
import org.apache.hadoop.hbase.client.HBaseAdmin;
// 37, 9
import org.apache.hadoop.hbase.client.HConnectionManager;
// 38, 9
import org.apache.hadoop.hbase.HTableDescriptor;
// 39, 9
import org.apache.hadoop.hbase.HBaseConfiguration;

public class FServerListTmplImpl
  extends org.jamon.AbstractTemplateImpl
  implements com.alibaba.wasp.tmpl.master.FServerListTmpl.Intf

{
  private final FMaster master;
  private final List<ServerName> servers;
  protected static com.alibaba.wasp.tmpl.master.FServerListTmpl.ImplData __jamon_setOptionalArguments(com.alibaba.wasp.tmpl.master.FServerListTmpl.ImplData p_implData)
  {
    if(! p_implData.getServers__IsNotDefault())
    {
      p_implData.setServers(null);
    }
    return p_implData;
  }
  public FServerListTmplImpl(org.jamon.TemplateManager p_templateManager, com.alibaba.wasp.tmpl.master.FServerListTmpl.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
    master = p_implData.getMaster();
    servers = p_implData.getServers();
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 43, 1
    if ((servers != null && servers.size() > 0))
    {
      // 43, 47
      jamonWriter.write("\n\n");
      // 45, 1
      
ServerName [] serverNames = servers.toArray(new ServerName[servers.size()]);
Arrays.sort(serverNames);

      // 50, 1
      jamonWriter.write("<div class=\"tabbable\">\n    <ul class=\"nav nav-pills\">\n        <li class=\"active\"><a href=\"#tab_baseStats\" data-toggle=\"tab\">Base Stats</a></li>\n        <li class=\"\"><a href=\"#tab_requestStats\" data-toggle=\"tab\">Requests</a></li>\n    </ul>\n    <div class=\"tab-content\" style=\"padding-bottom: 9px; border-bottom: 1px solid #ddd;\">\n        <div class=\"tab-pane active\" id=\"tab_baseStats\">\n            ");
      // 57, 13
      {
        // 57, 13
        __jamon_innerUnit__baseStats(jamonWriter, serverNames);
      }
      // 57, 56
      jamonWriter.write("\n        </div>\n        <div class=\"tab-pane\" id=\"tab_requestStats\">\n            ");
      // 60, 13
      {
        // 60, 13
        __jamon_innerUnit__requestStats(jamonWriter, serverNames);
      }
      // 60, 59
      jamonWriter.write("\n        </div>\n    </div>\n</div>\n\n");
    }
    // 65, 7
    jamonWriter.write("\n\n");
  }
  
  
  // 148, 1
  private void __jamon_innerUnit__requestStats(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final ServerName[] serverNames)
    throws java.io.IOException
  {
    // 152, 1
    jamonWriter.write("<table class=\"table table-striped\">\n<tr>\n    <th>ServerName</th>\n    <th>Request Per Second</th>\n    <th>Read Request Count</th>\n    <th>Write Request Count</th>\n</tr>\n");
    // 159, 1
    
for (ServerName serverName: serverNames) {

ServerLoad sl = master.getFServerManager().getLoad(serverName);
if (sl != null) {

    // 165, 1
    jamonWriter.write("<tr>\n<td>");
    // 166, 5
    {
      // 166, 5
      __jamon_innerUnit__serverNameLink(jamonWriter, serverName, sl);
    }
    // 166, 66
    jamonWriter.write("</td>\n<td>");
    // 167, 5
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(sl.getRequestsPerSecond()), jamonWriter);
    // 167, 36
    jamonWriter.write("</td>\n<td>");
    // 168, 5
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(sl.getReadRequestsCount()), jamonWriter);
    // 168, 36
    jamonWriter.write("</td>\n<td>");
    // 169, 5
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(sl.getWriteRequestsCount()), jamonWriter);
    // 169, 37
    jamonWriter.write("</td>\n</tr>\n");
    // 171, 1
    
        }  else {
        
    // 174, 1
    {
      // 174, 1
      __jamon_innerUnit__emptyStat(jamonWriter, serverName);
    }
    // 174, 40
    jamonWriter.write("\n");
    // 175, 1
    
        }
}

    // 179, 1
    jamonWriter.write("</table>\n");
  }
  
  
  // 257, 1
  private void __jamon_innerUnit__emptyStat(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final ServerName serverName)
    throws java.io.IOException
  {
    // 261, 5
    jamonWriter.write("<tr>\n    <td>");
    // 262, 9
    {
      // 262, 9
      __jamon_innerUnit__serverNameLink(jamonWriter, serverName, null);
    }
    // 262, 72
    jamonWriter.write("</td>\n    <td></td>\n    <td></td>\n    <td></td>\n    <td></td>\n    <td></td>\n    <td></td>\n    </tr>\n");
  }
  
  
  // 237, 1
  private void __jamon_innerUnit__serverNameLink(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final ServerName serverName, final ServerLoad serverLoad)
    throws java.io.IOException
  {
    // 242, 9
    
        boolean useDefault = (serverLoad == null);
        int defaultPort = master.getConfiguration().getInt("wasp.fserver.info.port", 50030);
        int infoPort = useDefault?defaultPort:serverLoad.getInfoServerPort();
        String url = "http://" + serverName.getHostname() + ":" + infoPort + "/fserver.jsp";
        String urlDefault = "http://" + serverName.getHostname() + ":" + defaultPort + "/fserver.jsp";
        
    // 250, 9
    if ((infoPort > 0) )
    {
      // 250, 31
      jamonWriter.write("\n            <a href=\"");
      // 251, 22
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(url), jamonWriter);
      // 251, 31
      jamonWriter.write("\">");
      // 251, 33
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(serverName.getHostname()), jamonWriter);
      // 251, 63
      jamonWriter.write("</a>\n        ");
    }
    // 252, 9
    else
    {
      // 252, 16
      jamonWriter.write("\n            <a href=\"");
      // 253, 22
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(urlDefault), jamonWriter);
      // 253, 38
      jamonWriter.write("\">");
      // 253, 40
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(serverName.getHostname()), jamonWriter);
      // 253, 70
      jamonWriter.write("<a>\n        ");
    }
    // 254, 15
    jamonWriter.write("\n");
  }
  
  
  // 113, 1
  private void __jamon_innerUnit__memoryStats(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final ServerName[] serverNames)
    throws java.io.IOException
  {
    // 117, 1
    jamonWriter.write("<table class=\"table table-striped\">\n<tr>\n    <th>ServerName</th>\n    <th>Used Heap</th>\n    <th>Max Heap</th>\n    <th>Memstore Size</th>\n\n</tr>\n");
    // 125, 1
    
for (ServerName serverName: serverNames) {

    ServerLoad sl = master.getFServerManager().getLoad(serverName);
    if (sl != null) {

    // 131, 1
    jamonWriter.write("<tr>\n    <td>");
    // 132, 9
    {
      // 132, 9
      __jamon_innerUnit__serverNameLink(jamonWriter, serverName, sl);
    }
    // 132, 70
    jamonWriter.write("</td>\n\n\n</tr>\n");
    // 136, 1
    
        }  else {

    // 139, 1
    {
      // 139, 1
      __jamon_innerUnit__emptyStat(jamonWriter, serverName);
    }
    // 139, 40
    jamonWriter.write("\n");
    // 140, 1
    
        }
}

    // 144, 1
    jamonWriter.write("</table>\n");
  }
  
  
  // 67, 1
  private void __jamon_innerUnit__baseStats(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final ServerName[] serverNames)
    throws java.io.IOException
  {
    // 71, 1
    jamonWriter.write("<table class=\"table table-striped\">\n<tr>\n    <th>ServerName</th>\n    <th>Start time</th>\n    <th>Requests Per Second</th>\n    <th>Num. EntityGroups</th>\n</tr>\n");
    // 78, 1
    
    int totalEntityGroups = 0;
    int totalRequests = 0;
    for (ServerName serverName: serverNames) {

    ServerLoad sl = master.getFServerManager().getLoad(serverName);
    double requestsPerSecond = 0.0;
    int numEntityGroupsOnline = 0;

    if (sl != null) {
        requestsPerSecond = sl.getRequestsPerSecond();
        numEntityGroupsOnline = sl.getNumberOfEntityGroups();
        totalEntityGroups += sl.getNumberOfEntityGroups();
        // Is this correct?  Adding a rate to a measure.
        totalRequests += sl.getNumberOfRequests();
    }
    long startcode = serverName.getStartcode();

    // 96, 1
    jamonWriter.write("<tr>\n    <td>");
    // 97, 9
    {
      // 97, 9
      __jamon_innerUnit__serverNameLink(jamonWriter, serverName, sl);
    }
    // 97, 70
    jamonWriter.write("</td>\n    <td>");
    // 98, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(new Date(startcode)), jamonWriter);
    // 98, 34
    jamonWriter.write("</td>\n    <td>");
    // 99, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(requestsPerSecond), jamonWriter);
    // 99, 32
    jamonWriter.write("</td>\n    <td>");
    // 100, 9
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(numEntityGroupsOnline), jamonWriter);
    // 100, 36
    jamonWriter.write("</td>\n</tr>\n");
    // 102, 1
    
}

    // 105, 1
    jamonWriter.write("<tr><td>Total:");
    // 105, 15
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(servers.size()), jamonWriter);
    // 105, 35
    jamonWriter.write("</td>\n<td></td>\n<td>");
    // 107, 5
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(totalRequests), jamonWriter);
    // 107, 24
    jamonWriter.write("</td>\n<td>");
    // 108, 5
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(totalEntityGroups), jamonWriter);
    // 108, 28
    jamonWriter.write("</td>\n</tr>\n</table>\n");
  }
  
  
  // 201, 1
  private void __jamon_innerUnit__compactionStats(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final ServerName[] serverNames)
    throws java.io.IOException
  {
    // 205, 1
    jamonWriter.write("<table class=\"table table-striped\">\n<tr>\n    <th>ServerName</th>\n    <th>Num. Compacting KVs</th>\n    <th>Num. Compacted KVs</th>\n    <th>Compaction Progress</th>\n</tr>\n");
    // 212, 1
    
for (ServerName serverName: serverNames) {

ServerLoad sl = master.getFServerManager().getLoad(serverName);
if (sl != null) {
String percentDone = "";


    // 220, 1
    jamonWriter.write("<tr>\n<td>");
    // 221, 5
    {
      // 221, 5
      __jamon_innerUnit__serverNameLink(jamonWriter, serverName, sl);
    }
    // 221, 66
    jamonWriter.write("</td>\n<\n<td>");
    // 223, 5
    org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(percentDone), jamonWriter);
    // 223, 22
    jamonWriter.write("</td>\n</tr>\n");
    // 225, 1
    
        }  else {
        
    // 228, 1
    {
      // 228, 1
      __jamon_innerUnit__emptyStat(jamonWriter, serverName);
    }
    // 228, 40
    jamonWriter.write("\n");
    // 229, 1
    
    }
}

    // 233, 1
    jamonWriter.write("</table>\n");
  }
  
  
  // 183, 1
  private void __jamon_innerUnit__storeStats(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter, final ServerName[] serverNames)
    throws java.io.IOException
  {
    // 187, 1
    jamonWriter.write("<table class=\"table table-striped\">\n<tr>\n    <th>ServerName</th>\n    <th>Num. Stores</th>\n    <th>Num. Storefiles</th>\n    <th>Storefile Size Uncompressed</th>\n    <th>Storefile Size</th>\n    <th>Index Size</th>\n    <th>Bloom Size</th>\n</tr>\n\n</table>\n");
  }
  
  
}