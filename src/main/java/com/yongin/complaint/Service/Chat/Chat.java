package com.yongin.complaint.Service.Chat;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.Payload.response.CreateCouponResponse;

public interface Chat {

    CreateCouponResponse createChatRoom(ChatRoomInfoDTO chatRoomInfoDTO);

    CreateCouponResponse dropChatRoom(ChatRoomInfoDTO chatRoomInfoDTO);

    CreateCouponResponse getChatRoomList();

    CreateCouponResponse getMyChatRoomList(String userId);
}
