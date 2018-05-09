package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

import java.util.Date;

public class MyInfoContributor implements InfoContributor {
    private Date added;
    private Date deleted;
    private Date updated;
    private Date read;
    private Date listed;

    @Override
    public void contribute(Info.Builder builder) {

    }
    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getRead() {
        return read;
    }

    public void setRead(Date read) {
        this.read = read;
    }

    public Date getListed() {
        return listed;
    }

    public void setListed(Date listed) {
        this.listed = listed;
    }

}
