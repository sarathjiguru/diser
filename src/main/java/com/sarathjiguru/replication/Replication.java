package com.sarathjiguru.replication;

import com.sarathjiguru.memory.DiserCommand;

/**
 * Created by sarath on 15/11/17.
 * <p>
 * Replicates data to other servers configured.
 */
public interface Replication {
    String replicate(String command);

    /**
     * Replicate the SET Command. If the SET command is already a replicated command then no action should be taken.
     * @param command
     * @return
     */
    String replicate(DiserCommand command);
}
