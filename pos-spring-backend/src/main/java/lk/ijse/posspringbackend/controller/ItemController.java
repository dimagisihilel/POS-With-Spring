package lk.ijse.posspringbackend.controller;

import lk.ijse.posspringbackend.customStatusCode.SelectedErrorStatus;
import lk.ijse.posspringbackend.dto.ItemStatus;
import lk.ijse.posspringbackend.dto.impl.ItemDTO;
import lk.ijse.posspringbackend.exception.DataPersistException;
import lk.ijse.posspringbackend.exception.ItemNotFoundException;
import lk.ijse.posspringbackend.service.ItemService;
import lk.ijse.posspringbackend.util.RegexUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDTO itemDTO) {
        try {
            itemService.saveItem(itemDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemStatus getSelectedItem(@PathVariable("itemId") String itemId) {
        String regexForItemId = RegexUtil.ITEM_ID_REGEX;
        Pattern regexpattern = Pattern.compile(regexForItemId);
        var regexMatcher = regexpattern.matcher(itemId);

        if (!regexMatcher.matches()) {
            return new SelectedErrorStatus(1, "ID not valid");
        }
        return itemService.getItem(itemId);
    }

    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("itemId") String itemId) {
        String regexForItemId = RegexUtil.ITEM_ID_REGEX;
        Pattern regexpattern = Pattern.compile(regexForItemId);
        var regexMatcher = regexpattern.matcher(itemId);

        try {
            if (!regexMatcher.matches()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.deleteItem(itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    @PutMapping(value = "/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateItem(@RequestBody ItemDTO itemDTO, @PathVariable("itemId") String itemId) {
        String regexForItemId = RegexUtil.ITEM_ID_REGEX;
        Pattern regexpattern = Pattern.compile(regexForItemId);
        var regexMatcher = regexpattern.matcher(itemId);

        try {
            if (!regexMatcher.matches()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemDTO.setItemId(itemId);
            itemService.updateItem(itemId, itemDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
