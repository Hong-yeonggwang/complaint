package com.yongin.complaint.DAO.Impl;

import com.fasterxml.jackson.databind.type.PlaceholderForType;
import com.yongin.complaint.DAO.AdminDAO;
import com.yongin.complaint.DTO.Admin.CategoryUpdateDTO;
import com.yongin.complaint.DTO.Admin.CouponStatisticsDTO;
import com.yongin.complaint.DTO.Admin.OperatorDTO;
import com.yongin.complaint.DTO.Admin.QRcodeStatisticsDTO;
import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.Place;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.JPA.Repository.*;
import com.yongin.complaint.Payload.response.Admin.CategoryDTO;
import com.yongin.complaint.Payload.response.Admin.CouponUseRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AdminDAOImpl implements AdminDAO {
    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final QRcodeCategoryRepository qrCodeCategoryRepository;
    private final QRcodeRepository qrCodeRepository;
    private final OperatorRepository operatorRepository;
    @Autowired
    public AdminDAOImpl(MemberRepository memberRepository,
                        CouponRepository couponRepository,
                        QRcodeCategoryRepository qrCodeCategoryRepository,
                        QRcodeRepository qrCodeRepository,
                        OperatorRepository operatorRepository){
        this.memberRepository = memberRepository;
        this.couponRepository = couponRepository;
        this.qrCodeRepository = qrCodeRepository;
        this.qrCodeCategoryRepository = qrCodeCategoryRepository;
        this.operatorRepository = operatorRepository;
    }

    @Override
    public List<QRcodeCategory> getAllCategory(){
        return qrCodeCategoryRepository.findAll();
    }

    @Override
    public void updateCategory(String placeName, String name, CategoryUpdateDTO updateInfo) {
        QRcodeCategory qRcodeCategory = qrCodeCategoryRepository.findByName(placeName,name);

        Place place = new Place();
        place.setPlaceSeq(updateInfo.getCategorySeq());

        qRcodeCategory.setQrcodeUsingPlace(place);
        qRcodeCategory.setPrice(updateInfo.getPrice());
        qRcodeCategory.setName(updateInfo.getName());

        qrCodeCategoryRepository.save(qRcodeCategory);
    }

    @Override
    public void addCategory(CategoryUpdateDTO updateInfo) {
        Place place = new Place();
        place.setPlaceSeq(updateInfo.getCategorySeq());
        QRcodeCategory qRcodeCategory = QRcodeCategory.builder()
                .name(updateInfo.getName())
                .price(updateInfo.getPrice())
                .qrcodeUsingPlace(place)
                .build();
        qrCodeCategoryRepository.save(qRcodeCategory);
    }

    @Override
    public void deleteCategory(String name) {
        QRcodeCategory qRcodeCategory = qrCodeCategoryRepository.findByName(name);
        qRcodeCategory.setStatus("hidden");

        qrCodeCategoryRepository.save(qRcodeCategory);
    }

    @Override
    public List<CouponUseRateResponse> getUseRateCoupon() {
        List<CouponStatisticsDTO> allCount = couponRepository.getCouponCount();
        List<CouponStatisticsDTO> useCount = couponRepository.getCouponUseCount();

        List<CouponUseRateResponse> returnDate = new ArrayList<>();

        for(CouponStatisticsDTO all : allCount){
            int flag = 0;
            for(CouponStatisticsDTO use : useCount){
                if(all.getPlaceName().equals(use.getPlaceName())){
                    returnDate.add(
                        CouponUseRateResponse.builder()
                            .rate(Map.of( "use", use.getCount(),"all",all.getCount()))
                            .placeName(all.getPlaceName())
                            .build()
                    );
                    flag = 1;
                }
            }
            if(flag == 0){
                returnDate.add(
                    CouponUseRateResponse.builder()
                        .rate(Map.of( "use", 0l,"all",all.getCount()))
                        .placeName(all.getPlaceName())
                        .build()
                );
            }
        }
        return returnDate;
    }

    @Override
    public List<CouponUseRateResponse> getUseRateQRcode() {

        List<QRcodeStatisticsDTO> allCount = qrCodeRepository.getQrcodeCount();
        List<QRcodeStatisticsDTO> useCount = qrCodeRepository.getQrcoceUseCount();

        List<CouponUseRateResponse> returnDate = new ArrayList<>();

        for(QRcodeStatisticsDTO all : allCount){
            int flag = 0;
            for(QRcodeStatisticsDTO use : useCount){
                if(all.getPlaceName().equals(use.getPlaceName())){
                    returnDate.add(
                        CouponUseRateResponse.builder()
                        .rate(Map.of( "use", use.getCount(),"all",all.getCount()))
                        .placeName(all.getPlaceName())
                        .build()
                    );
                    flag = 1;
                }
            }
            if(flag == 0){
                returnDate.add(
                    CouponUseRateResponse.builder()
                    .rate(Map.of( "use", 0l,"all",all.getCount()))
                    .placeName(all.getPlaceName())
                    .build()
                );
            }
        }
        return returnDate;
    }

    @Override
    public Long getUserCount() {
        return memberRepository.getUserCount();
    }

    @Override
    public Long getPlaceCount() {
        return qrCodeCategoryRepository.getPlaceCount();
    }

    @Override
    public Long getTodayUsedQRcode(String today) {
        return qrCodeRepository.getTodayUsedQrcodeCount(today);
    }

    @Override
    public Long getTodayCreatedQRcode(String today) {
        return qrCodeRepository.getTodayCreatedQrcodeCount(today);
    }

    @Override
    public List<QRcodeCategory> getCategoryInfo() {
        return qrCodeCategoryRepository.findAll();
    }

    @Override
    public List<Coupon> getCouponList() {
        return couponRepository.findAll();
    }

    @Override
    public Long getRemainQrcodeCount() {
        return qrCodeRepository.getRemainQrcodeCount();
    }

    @Override
    public List<OperatorDTO> getOperatorList() {
        return operatorRepository.getOperator();
    }

    @Override
    public CategoryDTO getCategoryList() {
        return CategoryDTO.builder()
                .build();
    }


}
