package won.shop.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import won.shop.Service.ItemService;
import won.shop.domain.Item;
import won.shop.dto.ItemFormDto;
import won.shop.dto.ItemFromDto;
import won.shop.dto.ItemSearchDto;

import java.beans.BeanInfo;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @GetMapping("/admin/item/new")
    public String itemForm(Model model){
        model.addAttribute("itemFormDto",new ItemFormDto());
        return "/item/itemForm";
    }

    @PostMapping("/admin/item/new")
    public String itemNew(@Validated ItemFormDto itemFormDto, BindingResult bindingResult, Model model,
                          @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList){

        if(bindingResult.hasErrors()){
            return "/item/itemForm";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() != null){
            model.addAttribute("errorMessage","첫번째 상품 이미지는 필수입니다.");
        }

        try {
            itemService.saveItem(itemFormDto,itemImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage","상품 등록중 에러가 발생했습니다.");
            return "/item/itemForm";
        }
            return "redirect:/";
    }

    @GetMapping("/admin/item/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model){
        try {
            ItemFormDto itemFormDto = itemService.getitemDtl(itemId);
            model.addAttribute("itemFormDto",itemFormDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage","존재하지 않는 상품입니다.");
            model.addAttribute("itemFormDto",new ItemFormDto());
            return "/item/itemForm";
        }
        return "/item/itemForm";
    }

    @PostMapping("/admin/item/{itemId}") //상품 수정
    public String itemUpdate(@Validated ItemFormDto itemFormDto, BindingResult bindingResult, Model model,
                          @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList){

        if(bindingResult.hasErrors()){
            return "/item/itemForm";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() != null){
            model.addAttribute("errorMessage","첫번째 상품 이미지는 필수입니다.");
        }

        try {
            itemService.updateItem(itemFormDto,itemImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage","상품 수정중 에러가 발생했습니다.");
            return "/item/itemForm";
        }
        return "redirect:/";
    }

    @GetMapping(value = {"/admin/items", "/admin/items/{page}"})
    public String itemManage(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "/item/itemMng";
    }

    @GetMapping("/item/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId){
        ItemFormDto itemFormDto = itemService.getitemDtl(itemId);
        model.addAttribute("item",itemFormDto);
        return "/item/itemDtl";
    }


}
