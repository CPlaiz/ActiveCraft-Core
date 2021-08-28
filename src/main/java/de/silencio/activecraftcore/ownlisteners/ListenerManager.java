package de.silencio.activecraftcore.ownlisteners;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {

    private List<StaffChatListener> staffChatListenerList = new ArrayList<>();
    private List<SocialSpyListener> socialSpyListenerList = new ArrayList<>();
    private List<VanishListener> vanishListenerList = new ArrayList<>();

    public void addListener(StaffChatListener listener) {
        staffChatListenerList.add(listener);
    }
    public void addListener(SocialSpyListener listener) {
        socialSpyListenerList.add(listener);
    }
    public void addListener(VanishListener listener) {
        vanishListenerList.add(listener);
    }

    public List<VanishListener> getVanishListenerList() {
        return vanishListenerList;
    }

    public List<StaffChatListener> getStaffChatListenerList() {
        return staffChatListenerList;
    }

    public List<SocialSpyListener> getSocialSpyListenerList() {
        return socialSpyListenerList;
    }
}
