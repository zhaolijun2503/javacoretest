package org.jee.lombok;

import java.beans.ConstructorProperties;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import lombok.NonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Something {
	
    private static final Logger log = LoggerFactory.getLogger(Something.class);
    private String name;
    private final String country;
    private final Object lockObj = new Object();
 
    public void sayHello(@NonNull String target) {
        if(target == null) {
            throw new NullPointerException("target");
        } else {
            String content = String.format("hello,%s", new Object[]{target});
            System.out.println(content);
            log.info(content);
        }
    }
 
    public void addBalabala() {
        ArrayList list = new ArrayList();
        list.add("haha");
        System.out.println(list.size());
    }
 
    public void closeBalabala() throws IOException {
        try {
            ByteArrayInputStream $ex = new ByteArrayInputStream("hello world".getBytes());
 
            try {
                System.out.println($ex.available());
            } finally {
                if(Collections.singletonList($ex).get(0) != null) {
                    $ex.close();
                }
 
            }
 
        } catch (IOException var6) {
            throw var6;
        }
    }
 
    public void lockMethod() {
        Object var1 = this.lockObj;
        synchronized(this.lockObj) {
            System.out.println("test lock method");
        }
    }
 
    public String getName() {
        return this.name;
    }
 
    public String getCountry() {
        return this.country;
    }
 
    public Object getLockObj() {
        return this.lockObj;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof Something)) {
            return false;
        } else {
            Something other = (Something)o;
            if(!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    String this$name = this.getName();
                    String other$name = other.getName();
                    if(this$name == null) {
                        if(other$name == null) {
                            break label47;
                        }
                    } else if(this$name.equals(other$name)) {
                        break label47;
                    }
 
                    return false;
                }
 
                String this$country = this.getCountry();
                String other$country = other.getCountry();
                if(this$country == null) {
                    if(other$country != null) {
                        return false;
                    }
                } else if(!this$country.equals(other$country)) {
                    return false;
                }
 
                Object this$lockObj = this.getLockObj();
                Object other$lockObj = other.getLockObj();
                if(this$lockObj == null) {
                    if(other$lockObj != null) {
                        return false;
                    }
                } else if(!this$lockObj.equals(other$lockObj)) {
                    return false;
                }
 
                return true;
            }
        }
    }
 
    protected boolean canEqual(Object other) {
        return other instanceof Something;
    }
 
    public int hashCode() {
        boolean PRIME = true;
        byte result = 1;
        String $name = this.getName();
        int result1 = result * 59 + ($name == null?0:$name.hashCode());
        String $country = this.getCountry();
        result1 = result1 * 59 + ($country == null?0:$country.hashCode());
        Object $lockObj = this.getLockObj();
        result1 = result1 * 59 + ($lockObj == null?0:$lockObj.hashCode());
        return result1;
    }
 
    public String toString() {
        return "Something(name=" + this.getName() + ", country=" + this.getCountry() + ", lockObj=" + this.getLockObj() + ")";
    }
 
    @ConstructorProperties({"name", "country"})
    public Something(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
